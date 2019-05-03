package com.heyx.jsoup.ctr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(value = {"/", "/index"})
    public String index(){
        return "view/index";
    }
}
