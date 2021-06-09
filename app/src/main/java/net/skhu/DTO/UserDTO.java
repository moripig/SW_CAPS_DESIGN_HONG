package net.skhu.DTO;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String users_id;
    private String users_name;
    private String users_pw;
    private String users_email;

    public String getUsers_email() {
        return users_email;
    }

    public void setUsers_email(String users_email) {
        this.users_email = users_email;
    }

    public UserDTO(){};

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getUsers_pw() {
        return users_pw;
    }

    public void setUsers_pw(String users_pw) {
        this.users_pw = users_pw;
    }

}
