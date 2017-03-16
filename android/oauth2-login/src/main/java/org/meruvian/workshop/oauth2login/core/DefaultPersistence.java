package org.meruvian.workshop.oauth2login.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by meruvian on 15/05/15.
 */
public class DefaultPersistence {
    protected String id;
    protected LogInformation logInformation = new LogInformation();
    @JsonIgnore
    private String refId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LogInformation getLogInformation() {
        return logInformation;
    }

    public void setLogInformation(LogInformation logInformation) {
        this.logInformation = logInformation;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
