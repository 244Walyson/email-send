package com.waly.emailsend.dto;

import com.waly.emailsend.entities.Verify;

import java.time.Instant;

public class VerifyDTO {

        private long id;
        private Integer code;
        private String email;
        private Instant expiration;

        public VerifyDTO (){}

    public VerifyDTO(long id, Integer code, String email, Instant expiration) {
        this.id = id;
        this.code = code;
        this.email = email;
        this.expiration = expiration;
    }

    public VerifyDTO(Verify entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.email = entity.getEmail();
        this.expiration = entity.getExpiration();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }
}
