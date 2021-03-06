package com.softuni.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @GetMapping("/create")
    public String createTeam(){

        return "team-create";
    }

    @GetMapping("/join")
    public String joinTeam(){

        return "join-team";
    }
}
