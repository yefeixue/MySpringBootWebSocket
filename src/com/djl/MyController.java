package com.djl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/hello")
public class MyController {
	
    @RequestMapping("/")
    public void greeting() {
        System.out.println("Hello World!");
    }
}
