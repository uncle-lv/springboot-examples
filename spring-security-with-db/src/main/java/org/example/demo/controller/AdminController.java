package org.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {
        model.addAttribute("user", principal);
        return "admin/admin";
    }
}
