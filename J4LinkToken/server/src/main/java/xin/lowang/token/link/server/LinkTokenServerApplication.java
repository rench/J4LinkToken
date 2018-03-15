package xin.lowang.token.link.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务应用
 */


@EnableAutoConfiguration
@SpringBootApplication
public class LinkTokenServerApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(LinkTokenServerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LinkTokenServerApplication.class, args);
    }
}
