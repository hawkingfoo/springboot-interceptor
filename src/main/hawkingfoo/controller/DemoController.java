package hawkingfoo.controller;

import hawkingfoo.annotation.Auth;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class DemoController {

    @Auth(user = "admin")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello world.";
    }
}
