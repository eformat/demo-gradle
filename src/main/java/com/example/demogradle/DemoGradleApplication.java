package com.example.demogradle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoGradleApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoGradleApplication.class, args);
    }
}
@RestController()
@RequestMapping("/api")
class HelloController {
    private static int counter = 0;
    @Autowired
    private HelloConfig config;

    @Value("#{environment.HELLO_USERNAME}")
    public String username;

    @Value("#{environment.HELLO_PASSWORD}")
    public String password;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public Map<String, Object> hello(@PathVariable String name) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        String resp = config.getMessage();
        if (null != username) {
            resp += " your username/password is: " + username + "/" + password;
        }
        response.put("response", resp);
        response.put("your-name", name);
        response.put("count", counter++);
        return response;
    }
}
