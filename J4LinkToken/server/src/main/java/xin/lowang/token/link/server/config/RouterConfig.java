package xin.lowang.token.link.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import xin.lowang.token.link.server.api.handler.CalculatorHandler;

@Configuration
public class RouterConfig {

    @Autowired
    @Bean
    public RouterFunction<ServerResponse> routerFunction(final CalculatorHandler calculatorHandler) {

        return RouterFunctions.route(RequestPredicates.path("/calc"), request ->
                request.queryParam("operator")
                        .map(operator -> {
                                    Mono obj =
                                            Mono.justOrEmpty(ReflectionUtils.findMethod(CalculatorHandler.class, operator, ServerRequest.class))
                                                    .flatMap(methodMono -> (Mono<ServerResponse>) ReflectionUtils.invokeMethod(methodMono, calculatorHandler, request))
                                                    .switchIfEmpty(ServerResponse.badRequest().build())
                                                    .onErrorResume(ex -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                                    return obj;
                                }
                        ).orElse(ServerResponse.badRequest().build())
        );

    }
}
