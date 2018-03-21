package xin.lowang.token.link.server;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import xin.lowang.token.link.server.api.vo.DeviceInfoVo;

public class WebSocketClientTest {
    private WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

    @Test
    public void testCreateUser() throws Exception{
        final DeviceInfoVo vo = new DeviceInfoVo();
        vo.setName("xxxx");
        vo.setIp("127.0.0.1");
        vo.setSn("SN00001");
        client.post().uri("/device")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(vo),DeviceInfoVo.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$[0].name").isEqualTo("xxxx");
    }
}
