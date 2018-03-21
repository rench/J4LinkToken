package xin.lowang.token.link.server;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ServerEventClientTest {
    public static void main(String[] args) throws InterruptedException {
        final WebClient client = WebClient.create("http://localhost:8080/device/num");
        client.get().uri("").accept(MediaType.TEXT_EVENT_STREAM).exchange()
                .flatMapMany(clientResponse -> clientResponse.body(BodyExtractors.toFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
                })))
                .filter(sse -> Objects.nonNull(sse.data()))
                .map(ServerSentEvent::data)
                .buffer(10)
                .doOnNext(System.out::println)
                .log()
                .subscribe();
        TimeUnit.MINUTES.sleep(2);
                //.blockFirst();


    }
}
