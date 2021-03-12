package com.softuni.model.service;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "gloves")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportGloveRootService {

    @XmlElement(name = "glove")
    private List<ImportGloveService> gloves;

    public ImportGloveRootService() {
    }


    public List<ImportGloveService> getGloves() {
        return gloves;
    }

    public void setGloves(List<ImportGloveService> gloves) {
        this.gloves = gloves;
    }
}
