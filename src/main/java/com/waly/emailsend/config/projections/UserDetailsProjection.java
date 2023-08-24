package com.waly.emailsend.config.projections;

public interface UserDetailsProjection {

    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
