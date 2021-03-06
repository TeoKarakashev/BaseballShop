package com.softuni.web;

import com.softuni.model.binding.UpdatePictureBindingModel;
import com.softuni.model.binding.UserRegisterBindingModel;
import com.softuni.model.service.UpdatePictureServiceModel;
import com.softuni.model.service.UserRegisterServiceModel;
import com.softuni.model.view.UserViewModel;
import com.softuni.service.UserService;
import com.softuni.util.annotation.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

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

    @ModelAttribute("emailExists")
    public boolean emailExists() {
        return false;
    }




    @PageTitle(name = "register")
    @GetMapping("/register")
    public String register(){
        return "/users/register";
    }


    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }


        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("passDontMatch", true);
            return "redirect:register";
        }

        if (this.userService.userExists(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:register";
        }

        if(this.userService.emailExists(userRegisterBindingModel.getEmail())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("emailExists", true);
            return "redirect:register";
        }

        this.userService.registerAndLoginUser(this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:/";
    }


    @PageTitle(name = "profile")
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(ModelAndView modelAndView, Principal principal){

        UserViewModel user = this.modelMapper.map(this.userService.findByUsername(principal.getName()), UserViewModel.class);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users/profile");

        return modelAndView;
    }


    @PageTitle(name = "login")
    @GetMapping("/login")
    public String login(){
        return "/users/login";
    }




    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                    RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();

        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);



        modelAndView.setViewName("redirect:login");

        return modelAndView;
    }

    @PostMapping("/updateProfilePicture")
    @PreAuthorize("isAuthenticated()")
    public String updatePicture(@ModelAttribute UpdatePictureBindingModel pictureBindingModel, Principal principal) throws IOException {
        this.userService.changePicture(this.modelMapper.map(pictureBindingModel, UpdatePictureServiceModel.class), principal.getName());
        return "redirect:profile";
    }

}
