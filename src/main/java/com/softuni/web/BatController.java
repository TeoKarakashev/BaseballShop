package com.softuni.web;

import com.softuni.model.binding.BatCreateBindingModel;
import com.softuni.model.service.BatServiceModel;
import com.softuni.model.service.BrandServiceModel;
import com.softuni.model.view.BatViewModel;
import com.softuni.service.BatService;
import com.softuni.service.BrandService;
import com.softuni.util.annotation.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bats")
public class BatController {

    private final BatService batService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;


    public BatController(BatService batService, BrandService brandService, ModelMapper modelMapper) {
        this.batService = batService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("batCreateBindingModel")
    public BatCreateBindingModel batCreateBindingModel() {
        return new BatCreateBindingModel();
    }

    @ModelAttribute("batExists")
    public boolean batExists() {
        return false;
    }

    @ModelAttribute("brands")
    List<BrandServiceModel> brands() {
        return this.brandService.findAllBrands();
    }


    @PageTitle(name = "details")
    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(@PathVariable String id, ModelAndView modelAndView) {
        BatViewModel batViewModel = this.batService.findById(id);
        modelAndView.addObject("bat", batViewModel);
        modelAndView.setViewName("/bats/bats-details");
        return modelAndView;
    }

    @PageTitle(name = "viewALl")
    @GetMapping("/viewAll")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewAll(ModelAndView modelAndView) {
        modelAndView.addObject("bats", this.batService.findAllBats());
        modelAndView.setViewName("/bats/bats-all");

        return modelAndView;
    }

    @PageTitle(name = "viewRawlings")
    @GetMapping("/showRawlings")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showRawlings(ModelAndView modelAndView) {
        modelAndView.addObject("bats",
                this.batService.findByBrand("Rawlings"));


        modelAndView.setViewName("/bats/bats-all");
        return modelAndView;
    }

    @PageTitle(name = "viewWilson")
    @GetMapping("/showWilson")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showWilson(ModelAndView modelAndView) {

        modelAndView.addObject("bats",
                this.batService.findByBrand("Wilson"));
        modelAndView.setViewName("/bats/bats-all");
        return modelAndView;
    }

    @PageTitle(name = "viewE7")
    @GetMapping("/showE7")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showE7(ModelAndView modelAndView) {
        modelAndView.addObject("bats",
                this.batService.findByBrand("E7"));


        modelAndView.setViewName("/bats/bats-all");
        return modelAndView;
    }

    @PostMapping("/buy/{id}")
    @PreAuthorize("isAuthenticated()")
    public String buy(@PathVariable String id, Principal principal) {
        this.batService.buy(id, principal.getName());
        return "redirect:/";
    }

    @PageTitle(name = "add")
    @GetMapping("/add")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String create() {
        return "/bats/bats-create";
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String createConfirm(@Valid BatCreateBindingModel batCreateBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("batCreateBindingModel", batCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.batCreateBindingModel", bindingResult);
            return "redirect:add";
        }

        if (this.batService.batExists(batCreateBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("batCreateBindingModel", batCreateBindingModel);
            redirectAttributes.addFlashAttribute("batExists", true);
            return "redirect:add";
        }
        this.batService.save(this.modelMapper.map(batCreateBindingModel, BatServiceModel.class));


        return "redirect:/";
    }
}

