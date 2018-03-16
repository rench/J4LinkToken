package xin.lowang.token.link.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;
import xin.lowang.token.link.server.api.vo.DeviceInfoVo;
import xin.lowang.token.link.server.exception.ResourceNotFoundException;
import xin.lowang.token.link.server.service.DeviceInfoService;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/device")
public class DeviceInfoRestController {

    private DeviceInfoService deviceInfoService;

    @Autowired
    public void setDeviceInfoService(DeviceInfoService deviceInfoService) {
        this.deviceInfoService = deviceInfoService;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void notFound() {

    }

    @GetMapping("/{sn}")
    public Mono<DeviceInfoVo> getBySn(@PathVariable("sn") String sn) {
        return deviceInfoService.getBySn(sn);
    }

    @GetMapping("")
    public Flux<DeviceInfoVo> list() {
        return deviceInfoService.list();
    }

    @PostMapping("")
    public Flux<DeviceInfoVo> create(@RequestBody final Flux<DeviceInfoVo> infos) {
        return deviceInfoService.save(infos);
    }

    @PutMapping("/{sn}")
    public Mono<DeviceInfoVo> update(@PathVariable("sn") String sn, @RequestBody DeviceInfoVo info) {
        Objects.requireNonNull(info);
        info.setSn(sn);
        return deviceInfoService.save(info);
    }

    @DeleteMapping("/{sn}")
    public Mono<DeviceInfoVo> delete(@PathVariable("sn") String sn) {
        return deviceInfoService.delete(sn);
    }

    @PostMapping("/sns")
    public Flux<DeviceInfoVo> getBySn(@RequestBody final Mono<List<String>> sns) {
        ThreadLocalRandom.current().nextBoolean();
        return deviceInfoService.getBySn(sns.flatMapMany(Flux::fromIterable));
    }

    @GetMapping("/num")
    public Flux<ServerSentEvent<Integer>> deviceNum() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> Tuples.of(i, ThreadLocalRandom.current().nextInt()))
                .map(i -> ServerSentEvent.<Integer>builder()
                        .event("num")
                        .id(Long.toString(i.getT1()))
                        .data(i.getT2()).build());
    }
}
