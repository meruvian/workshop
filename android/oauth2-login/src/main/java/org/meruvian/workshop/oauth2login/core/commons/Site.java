package org.meruvian.workshop.oauth2login.core.commons;


import org.meruvian.workshop.oauth2login.core.DefaultPersistence;

/**
 * Created by meruvian on 10/09/15.
 */
public class Site extends DefaultPersistence {
    private String name;
    private String description;
    private String title;
    private String urlBranding;
    private String siteUrl;
    private String adminEmail;
    private int notifyFlag = 0;
    private String notifyEmail;
    private String notifyFrom;
    private String notifyMessage;
    private String workspaceType;
    private Site master;
//    private List<Site> sites = new ArrayList<Site>();
    private String virtualhost;
    private String path = "";
    private int level = 0;
    private String theme;
    private String verytransId = "";
//    private List<RoleSite> role = new ArrayList<RoleSite>();
    private String address;
    private String phone;
    private String fax;
    private String email;
    private String npwp;
    private String postalCode;
    private String city;
    private String type;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlBranding() {
        return urlBranding;
    }

    public void setUrlBranding(String urlBranding) {
        this.urlBranding = urlBranding;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public int getNotifyFlag() {
        return notifyFlag;
    }

    public void setNotifyFlag(int notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getNotifyFrom() {
        return notifyFrom;
    }

    public void setNotifyFrom(String notifyFrom) {
        this.notifyFrom = notifyFrom;
    }

    public String getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
    }

    public String getWorkspaceType() {
        return workspaceType;
    }

    public void setWorkspaceType(String workspaceType) {
        this.workspaceType = workspaceType;
    }

    public Site getMaster() {
        return master;
    }

    public void setMaster(Site master) {
        this.master = master;
    }

    public String getVirtualhost() {
        return virtualhost;
    }

    public void setVirtualhost(String virtualhost) {
        this.virtualhost = virtualhost;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getVerytransId() {
        return verytransId;
    }

    public void setVerytransId(String verytransId) {
        this.verytransId = verytransId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
