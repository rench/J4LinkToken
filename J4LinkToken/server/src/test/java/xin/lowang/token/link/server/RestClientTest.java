package xin.lowang.token.link.server;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import xin.lowang.token.link.server.api.vo.DeviceInfoVo;

public class RestClientTest {

    public static void main(String[] args) {
        final DeviceInfoVo vo = new DeviceInfoVo();
        vo.setSn("123123");
        vo.setIp("127.0.0.1");
        vo.setName("device01");
        final WebClient client = WebClient.create("http://localhost:8080/device");
        final Mono<DeviceInfoVo> device = client.post()
                .uri("")
                .body(Mono.just(vo),DeviceInfoVo.class)
                .exchange()
                .flatMap( clientResponse -> clientResponse.bodyToFlux(DeviceInfoVo.class).singleOrEmpty());
        System.out.println(device.block());
    }
}
