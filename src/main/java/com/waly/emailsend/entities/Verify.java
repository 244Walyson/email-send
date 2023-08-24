package com.waly.emailsend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_verify")
public class Verify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private Integer code;

    @ManyToOne
    private User user;

    public Verify(){}

    public Verify(long id, Integer code) {
        Id = id;
        this.code = code;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
