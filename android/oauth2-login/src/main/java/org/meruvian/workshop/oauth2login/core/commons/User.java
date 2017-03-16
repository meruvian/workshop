package org.meruvian.workshop.oauth2login.core.commons;


import org.meruvian.workshop.oauth2login.core.DefaultPersistence;

/**
 * Created by dianw on 11/6/14.
 */
public class User extends DefaultPersistence {
    private String username;
    private String password;
    private String email;
    private Name name = new Name();
    private Address address = new Address();
    private FileInfo fileInfo = new FileInfo();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

}
