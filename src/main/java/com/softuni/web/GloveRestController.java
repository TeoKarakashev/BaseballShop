package com.softuni.web;


import com.softuni.model.view.GloveViewModel;
import com.softuni.service.GloveService;
import com.softuni.web.annotation.PageTitle;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gloves")
public class GloveRestController {

    private final GloveService gloveService;
    private final ModelMapper modelMapper;

    public GloveRestController(GloveService productService, ModelMapper modelMapper) {
        this.gloveService = productService;
        this.modelMapper = modelMapper;
    }


    @PageTitle("Gloves")
    @GetMapping("/api")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<GloveViewModel>> findAll() {
        return ResponseEntity
                .ok()
                .body(this.modelMapper.map(gloveService.findAllGloves(),
                        new TypeToken<List<GloveViewModel>>(){}.getType()));
    }
}
