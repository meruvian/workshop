package org.meruvian.workshop.oauth2login.core.commons;


import org.meruvian.workshop.oauth2login.core.DefaultPersistence;

/**
 * Created by root on 19/11/14.
 */
public class UserRole extends DefaultPersistence {
    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
