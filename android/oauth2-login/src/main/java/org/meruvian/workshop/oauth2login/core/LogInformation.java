package org.meruvian.workshop.oauth2login.core;

import java.util.Date;

/**
 * Created by meruvian on 15/05/15.
 */
public class LogInformation {
    private Date createDate = new Date();
    private Date lastUpdateDate = new Date();
    private String createBy;
    private String lastUpdateBy;
    private int activeFlag = ACTIVE;
    private String site;

    public final static int ACTIVE = 1;
    public final static int INACTIVE = 0;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }

    public boolean getActive() {
        return (getActiveFlag() == ACTIVE);
    }

    public boolean getInactive() {
        return (getActiveFlag() == INACTIVE);
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
