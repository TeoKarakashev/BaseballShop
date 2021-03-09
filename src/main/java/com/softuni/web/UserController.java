package com.softuni.web;

import com.softuni.model.binding.UserRegisterBindingModel;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.service.UserRegisterServiceModel;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel createBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("userExists")
    public boolean userExists() {
        return false;
    }

    @ModelAttribute("passDontMatch")
    public boolean passDontMatch() {
        return false;
    }




    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        //TODO field match validator MusicDB
        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("passDontMatch", true);
            return "redirect:register";
        }

        if (userService.userExists(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:register";
        }

        //TODO check if email already exists

        this.userService.registerAndLoginUser(this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:/";
    }




    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }


    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("bad_credentials", true);
        modelAndView.addObject("username", username);

        modelAndView.setViewName("/login");

        return modelAndView;
    }

}
