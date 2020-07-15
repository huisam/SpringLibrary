package com.huisam.springstudy.filter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/view")
    public String view() {
        return "redirect:web";
    }

    @GetMapping("/web")
    public String web() {
        return "view";
    }
}

