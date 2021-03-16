package com.softuni.web;

import com.softuni.error.GloveNotFoundException;
import com.softuni.model.view.GloveViewModel;
import com.softuni.repository.BrandRepository;
import com.softuni.service.BrandService;
import com.softuni.service.GloveService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gloves")
public class GloveController {

    private final GloveService gloveService;
    private final BrandService brandService;
    private final BrandRepository brandRepository;

    public GloveController(GloveService gloveService, BrandService brandService, BrandRepository brandRepository) {
        this.gloveService = gloveService;
        this.brandService = brandService;
        this.brandRepository = brandRepository;
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(ModelAndView modelAndView,  @PathVariable String id) {
        GloveViewModel glove = this.gloveService.findById(id);
        modelAndView.addObject("glove", glove);
        modelAndView.setViewName("gloves-details");

        return modelAndView;
    }


    @GetMapping ("/viewAll")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewAll(ModelAndView modelAndView){
        modelAndView.addObject("gloves", this.gloveService.findAllGloves());
        modelAndView.setViewName("gloves-all");

        return modelAndView;
    }

    @GetMapping("/showRawlings")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showRawlings(ModelAndView modelAndView) {
        modelAndView.addObject("gloves",
                this.gloveService.findByBrand(this.brandRepository.findByName("Rawlings").orElseThrow(() ->new GloveNotFoundException("No glove found"))));


        modelAndView.setViewName("gloves-all");
        return modelAndView;
    }
    @GetMapping("/showWilson")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showWilson(ModelAndView modelAndView) {

        modelAndView.addObject("gloves",
                this.gloveService.findByBrand(this.brandRepository.findByName("Wilson").orElseThrow(() ->new GloveNotFoundException("No glove found"))));
        modelAndView.setViewName("gloves-all");
        return modelAndView;
    }
    @GetMapping("/showE7")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showE7(ModelAndView modelAndView) {
        modelAndView.addObject("gloves",
                this.gloveService.findByBrand(this.brandRepository.findByName("E7").orElseThrow(() ->new GloveNotFoundException("No glove found"))));


        modelAndView.setViewName("gloves-all");
        return modelAndView;
    }
}
