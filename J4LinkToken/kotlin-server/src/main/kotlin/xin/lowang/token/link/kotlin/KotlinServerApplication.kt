package xin.lowang.token.link.kotlin

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlinServerApplication{
    private  val log = LoggerFactory.getLogger(KotlinServerApplication::class.java)
}


fun main(args: Array<String>) {
    runApplication<KotlinServerApplication>(*args)
}