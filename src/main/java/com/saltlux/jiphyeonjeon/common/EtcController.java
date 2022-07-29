package com.saltlux.jiphyeonjeon.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtcController {

    @GetMapping("/errorPage")
    public String error(){
        return "errorView";
    }
}
