package com.waly.emailsend.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    private String imgUrl;


    @ManyToMany
    @JoinTable(name = "tb_project_technology",
                joinColumns = @JoinColumn(name = "project"),
                inverseJoinColumns = @JoinColumn(name = "technology"))
    private Set<Technology> technologies = new HashSet<>();

    public Project(){}
    public Project(long id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
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

    public Set<Technology> getTechnologies() {
        return technologies;
    }

    public void AddTechnologies(Technology technology) {
        this.technologies.add(technology);
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
