package com.unican.polaflix.restctrl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @CrossOrigin(origins = {"http://127.0.0.1:3001", "http://127.0.0.1:8080"})
    @RequestMapping("/")
    public String welcome() {
        return "index";
    }
}
