package org.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(Principal principal, Model model) {
        model.addAttribute("user", principal);
        return "index";
    }
}
