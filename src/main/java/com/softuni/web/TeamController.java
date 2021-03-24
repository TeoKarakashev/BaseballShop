package com.softuni.web;

import com.softuni.service.TeamService;
import com.softuni.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final UserService userService;

    public TeamController(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String createTeam(){

        return "team-create";
    }

    @GetMapping("")
    public ModelAndView teams(ModelAndView modelAndView){
        modelAndView.addObject("teams", this.teamService.findAllTeams());
        modelAndView.setViewName("teams-all");
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(ModelAndView modelAndView, @PathVariable String id){

        modelAndView.addObject("names", this.teamService.findAllPlayersNames(id));
        modelAndView.addObject("team",this.teamService.findById(id));
        modelAndView.addObject("canLeave", true);
        modelAndView.addObject("canJoin", true);
        modelAndView.addObject("isCreator", true);

        modelAndView.setViewName("team-details");

        return modelAndView;
    }

    @PostMapping("/join/{id}")
    public String join(@PathVariable String id, Principal principal){
        this.teamService.addPlayerToTeam(id, principal.getName());

        return "redirect:/";
    }
}
