package com.softuni.web;

import com.softuni.model.view.BatViewModel;
import com.softuni.service.BatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/bats")
public class BatController {

    private final BatService batService;


    public BatController(BatService batService) {
        this.batService = batService;
    }


    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(@PathVariable String id, ModelAndView modelAndView){
        BatViewModel batViewModel = this.batService.findById(id);
        modelAndView.addObject("bat", batViewModel);
        modelAndView.setViewName("bats-details");
        return modelAndView;
    }

    @GetMapping ("/viewAll")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewAll(ModelAndView modelAndView){
        modelAndView.addObject("bats", this.batService.findAllBats());
        modelAndView.setViewName("bats-all");

        return modelAndView;
    }

    @GetMapping("/showRawlings")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showRawlings(ModelAndView modelAndView) {
        modelAndView.addObject("bats",
                this.batService.findByBrand("Rawlings"));


        modelAndView.setViewName("bats-all");
        return modelAndView;
    }
    @GetMapping("/showWilson")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showWilson(ModelAndView modelAndView) {

        modelAndView.addObject("bats",
                this.batService.findByBrand("Wilson"));
        modelAndView.setViewName("bats-all");
        return modelAndView;
    }
    @GetMapping("/showE7")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showE7(ModelAndView modelAndView) {
        modelAndView.addObject("bats",
                this.batService.findByBrand("E7"));


        modelAndView.setViewName("bats-all");
        return modelAndView;
    }

    @PostMapping("/buy/{id}")
    public String buy(@PathVariable String id, Principal principal){
     this.batService.buy(id, principal.getName());
        return "redirect:/";
    }
}
