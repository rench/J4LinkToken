package xin.lowang.token.link.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xin.lowang.token.link.server.api.vo.DeviceInfoVo;

@RestController("homeRestController")
@RequestMapping({"", "/"})
public class HomeRestController {

    public static void main(String[] args) {
        Flux.just("say", "hello", "你", "好").log().subscribe();
    }

    @RequestMapping("")
    public String home() {
        return "垃圾哈哈";
    }

    @RequestMapping("say")
    public ResponseEntity<Mono<DeviceInfoVo>> say() {
        DeviceInfoVo vo = new DeviceInfoVo();
        vo.setName("test");
        vo.setSn("xxxx");
        vo.setIp("127.0.0.1");
        return ResponseEntity.ok(Mono.just(vo));
    }

    @RequestMapping("sayHello")
    public Flux<String> sayHello() {

        return Flux.empty();
    }

    @RequestMapping("/list")
    public Flux<DeviceInfoVo> list() {
        DeviceInfoVo vo = new DeviceInfoVo();
        vo.setName("test");
        vo.setSn("xxxx");
        vo.setIp("127.0.0.1");
        return Flux.just(vo);
    }

    @RequestMapping("/one")
    public Mono<DeviceInfoVo> one() {
        DeviceInfoVo vo = new DeviceInfoVo();
        vo.setName("test");
        vo.setSn("xxxx");
        vo.setIp("127.0.0.1");
        return Mono.just(vo);
    }

}
