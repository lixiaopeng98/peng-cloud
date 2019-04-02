package cn.peng.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping(value = "/byId")
    public Principal byId(Principal principal) {
        return principal;
    }
}
