package com.waly.emailsend.dto;

import com.waly.emailsend.entities.Technology;

public class TechnologyDTO {

    private long id;
    private String name;
    private String imgUrl;

    public TechnologyDTO(){}

    public TechnologyDTO(long id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public TechnologyDTO(Technology entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imgUrl = entity.getImgUrl();
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

