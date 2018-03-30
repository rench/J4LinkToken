package xin.lowang.token.link.kotlin.web

import com.alibaba.fastjson.JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kotlin")
class UserController {
    @GetMapping("/")
    fun index(): String {
        var resultMap = HashMap<String, Any>()
        resultMap["status"] = true
        resultMap["msg"] = "hello kotlin!"
        return JSON.toJSONString(resultMap)
    }

    @GetMapping("/test")
    fun test() = "kotlin test"

    @GetMapping("/test2")
    fun test2(){

    }
}