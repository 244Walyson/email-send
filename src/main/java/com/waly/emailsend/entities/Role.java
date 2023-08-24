package com.waly.emailsend.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String authority;

    public Role(){}

    public Role(long id, String authority) {
        Id = id;
        this.authority = authority;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        authority = authority;
    }
}
