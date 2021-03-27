package com.softuni.model.view;

import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.GloveEntity;
import com.softuni.model.entity.TeamEntity;

public class UserViewModel {

    private String firstName;
    private String lastName;
    private TeamEntity team;
    private String email;
    private BatEntity bat;
    private GloveEntity glove;
    private String imageUrl;

    public UserViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BatEntity getBat() {
        return bat;
    }

    public void setBat(BatEntity bat) {
        this.bat = bat;
    }

    public GloveEntity getGlove() {
        return glove;
    }

    public void setGlove(GloveEntity glove) {
        this.glove = glove;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
