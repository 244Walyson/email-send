package com.waly.emailsend.dto;

import com.waly.emailsend.entities.Project;
import com.waly.emailsend.entities.Technology;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDTO {

    private long id;
    private String name;
    private String description;

    private String imgUrl;
    private Set<TechnologyDTO> technologiesDto = new HashSet<>();

    public ProjectDTO(){}

    public ProjectDTO(long id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }
    public ProjectDTO(Project entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imgUrl = entity.getImgUrl();
        this.technologiesDto = entity.getTechnologies().stream().map(TechnologyDTO::new).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<TechnologyDTO> getTechnologies() {
        return technologiesDto;
    }

    public void AddTechnologies(TechnologyDTO technologies) {
        this.technologiesDto.add(technologies);
    }
}
