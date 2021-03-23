package com.softuni.web;

import com.softuni.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
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
        modelAndView.addObject("team",this.teamService.findById(id));
        modelAndView.setViewName("team-details");

        return modelAndView;
    }
}
