package xin.lowang.token.link.server.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xin.lowang.token.link.server.api.vo.DeviceInfoVo;
import xin.lowang.token.link.server.exception.ResourceNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {
    private static final Map<String, DeviceInfoVo> data = new ConcurrentHashMap<>();

    @Override
    public Flux<DeviceInfoVo> list() {
        return Flux.fromIterable(data.values());
    }

    @Override
    public Mono<DeviceInfoVo> getBySn(String sn) {
        return Mono.justOrEmpty(data.get(sn)).switchIfEmpty(Mono.error(new ResourceNotFoundException("未找到")));
    }

    @Override
    public Flux<DeviceInfoVo> getBySn(Flux<String> sns) {
        return sns.flatMap((sn) -> {
            System.out.println(sn);
            return Mono.justOrEmpty(data.get(sn));
        });
    }

    @Override
    public Flux<DeviceInfoVo> save(Flux<DeviceInfoVo> infos) {
        return infos.doOnNext(info -> data.put(info.getSn(), info));
    }

    @Override
    public Mono<DeviceInfoVo> save(DeviceInfoVo info) {
        data.put(info.getSn(), info);
        return Mono.just(info);
    }

    @Override
    public Mono<DeviceInfoVo> delete(String sn) {
        return Mono.justOrEmpty(data.remove(sn));
    }
}
