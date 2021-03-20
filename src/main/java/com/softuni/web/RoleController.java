package com.softuni.web;

import com.softuni.service.UserService;
import com.softuni.web.annotation.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    @PageTitle("Roles Manager")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ModelAndView add(ModelAndView modelAndView, Principal principal) {


      //  modelAndView.addObject("usernames", this.userService.findAllUsernames(principal.getName()));
        modelAndView.setViewName("admin-panel");

        return modelAndView;
    }

//    @PostMapping("/add")
//    public String addConfirm(@ModelAttribute("roleAddBindingModel")
//                                     RoleAddBindingModel roleAddBindingModel) {
//
//        this.userService.addRoleToUser(roleAddBindingModel.getUsername(), roleAddBindingModel.getRole());
//
//        return "redirect:/";
//    }
}
