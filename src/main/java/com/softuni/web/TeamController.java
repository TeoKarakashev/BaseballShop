package com.softuni.web;

import com.softuni.model.binding.TeamCreateBindingModel;
import com.softuni.model.service.TeamServiceModel;
import com.softuni.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final ModelMapper modelMapper;


    @Autowired
    public TeamController(TeamService teamService, ModelMapper modelMapper) {
        this.teamService = teamService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("teamCreateBindingModel")
    public TeamCreateBindingModel teamCreateBindingModel() {return new TeamCreateBindingModel();}

    @ModelAttribute("teamExists")
    public Boolean teamExists() {return false;}


    @GetMapping("/create")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String create(){

        return "/teams/team-create";
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String createConfirm(@Valid @ModelAttribute("teamCreateBindingModel")TeamCreateBindingModel teamCreateBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) throws IOException {
        System.out.println();
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("teamCreateBindingModel", teamCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.teamCreateBindingModel", bindingResult);
            return "redirect:create";
        }

        if (this.teamService.teamExists(teamCreateBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("teamCreateBindingModel", teamCreateBindingModel);
            redirectAttributes.addFlashAttribute("teamExists", true);
            return "redirect:create";
        }
        this.teamService.saveTeam(this.modelMapper.map(teamCreateBindingModel, TeamServiceModel.class), principal.getName());

        return "redirect:/";
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView teams(ModelAndView modelAndView){
        modelAndView.addObject("teams", this.teamService.findAllTeams());
        modelAndView.setViewName("/teams/teams-all");
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(ModelAndView modelAndView, @PathVariable String id, Principal principal){

        modelAndView.addObject("names", this.teamService.findAllPlayersNames(id));
        modelAndView.addObject("team",this.teamService.findById(id));
        modelAndView.addObject("canLeave", this.teamService.userIsPartOfTheTeam(id, principal.getName()));
        modelAndView.addObject("canJoin", this.teamService.userCanJoin(id, principal.getName()));
        modelAndView.addObject("isCreator", this.teamService.isCreator(id, principal.getName()));

        modelAndView.setViewName("/teams/team-details");

        return modelAndView;
    }

    @PostMapping("/join/{id}")
    @PreAuthorize("isAuthenticated()")
    public String join(@PathVariable String id, Principal principal){
        this.teamService.addPlayerToTeam(id, principal.getName());
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete(@PathVariable String id){
        this.teamService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/leave/{id}")
    @PreAuthorize("isAuthenticated()")
    public String leave(@PathVariable String id, Principal principal){
        this.teamService.removePlayerFromTeam(id, principal.getName());

        return "redirect:/";
    }

}
