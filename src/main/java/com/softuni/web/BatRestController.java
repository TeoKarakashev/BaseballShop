package com.softuni.web;

import com.softuni.model.view.BatViewModel;
import com.softuni.service.BatService;
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
@RequestMapping("/bats")
public class BatRestController {

    private final BatService batService;
    private final ModelMapper modelMapper;

    public BatRestController(BatService batService, ModelMapper modelMapper) {
        this.batService = batService;
        this.modelMapper = modelMapper;
    }


    @PageTitle("Bats")
    @GetMapping("/api")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BatViewModel>> findAll() {
        return ResponseEntity
                .ok()
                .body(this.modelMapper.map(batService.findAllBats(),
                        new TypeToken<List<BatViewModel>>(){}.getType()));
    }

}
