package com.softuni.web;

import com.softuni.model.binding.GloveCreateBindingModel;
import com.softuni.model.service.BrandServiceModel;
import com.softuni.model.service.GloveServiceModel;
import com.softuni.model.view.GloveViewModel;
import com.softuni.service.BrandService;
import com.softuni.service.GloveService;
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
@RequestMapping("gloves")
public class GloveController {

    private final GloveService gloveService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public GloveController(GloveService gloveService, BrandService brandService, ModelMapper modelMapper) {
        this.gloveService = gloveService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("gloveCreateBindingModel")
    public GloveCreateBindingModel gloveCreateBindingModel() {
        return new GloveCreateBindingModel();
    }

    @ModelAttribute("gloveExists")
    public boolean gloveExists() {
        return false;
    }

    @ModelAttribute("brands")
     List<BrandServiceModel> brands() {
        return this.brandService.findAllBrands();
    }

    @PageTitle(name = "details")
    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(ModelAndView modelAndView, @PathVariable String id) {
        GloveViewModel glove = this.gloveService.findById(id);
        modelAndView.addObject("glove", glove);
        modelAndView.setViewName("/gloves/gloves-details");

        return modelAndView;
    }


    @PageTitle(name = "viewAll")
    @GetMapping("/viewAll")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewAll(ModelAndView modelAndView) {
        modelAndView.addObject("gloves", this.gloveService.findAllGloves());
        modelAndView.setViewName("/gloves/gloves-all");

        return modelAndView;
    }

    @PageTitle(name = "viewRawlings")
    @GetMapping("/showRawlings")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showRawlings(ModelAndView modelAndView) {
        modelAndView.addObject("gloves",
                this.gloveService.findByBrand("Rawlings"));


        modelAndView.setViewName("/gloves/gloves-all");
        return modelAndView;
    }

    @PageTitle(name = "viewWilson")
    @GetMapping("/showWilson")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showWilson(ModelAndView modelAndView) {

        modelAndView.addObject("gloves",
                this.gloveService.findByBrand("Wilson"));
        modelAndView.setViewName("/gloves/gloves-all");
        return modelAndView;
    }

    @PageTitle(name = "viewE7")
    @GetMapping("/showE7")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showE7(ModelAndView modelAndView) {
        modelAndView.addObject("gloves",
                this.gloveService.findByBrand("E7"));


        modelAndView.setViewName("/gloves/gloves-all");
        return modelAndView;
    }

    @PostMapping("/buy/{id}")
    @PreAuthorize("isAuthenticated()")
    public String buy(@PathVariable String id, Principal principal) {
        this.gloveService.buy(id, principal.getName());
        return "redirect:/";
    }

    @PageTitle(name = "add")
    @GetMapping("/add")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String create() {
        return "/gloves/gloves-create";
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String createConfirm(@Valid GloveCreateBindingModel gloveCreateBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("gloveCreateBindingModel", gloveCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gloveCreateBindingModel", bindingResult);
            return "redirect:add";
        }

        if (this.gloveService.gloveExists(gloveCreateBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("gloveCreateBindingModel", gloveCreateBindingModel);
            redirectAttributes.addFlashAttribute("gloveExists", true);
            return "redirect:add";
        }

        this.gloveService.save(this.modelMapper.map(gloveCreateBindingModel, GloveServiceModel.class));

        return "redirect:/";
    }
}
