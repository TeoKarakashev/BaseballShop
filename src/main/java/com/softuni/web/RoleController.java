package com.softuni.web;

import com.softuni.service.UserService;
import com.softuni.util.annotation.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @PageTitle(name = "add")
    @GetMapping("/add")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ModelAndView add(ModelAndView modelAndView) {

        modelAndView.addObject("usernames", this.userService.findAllUsers());
        modelAndView.setViewName("/users/admin-panel");

        return modelAndView;
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String addConfirm(@RequestParam String username){
        this.userService.promoteUserToAdmin(username);
        return "redirect:/";
    }
}
