package com.javartisan.bean;

public class UserInfo {

    private String id;
    private String userName;
    private String passWord;

    public UserInfo(String id, String userName, String passWord, String user_email) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.user_email = user_email;
    }

    public UserInfo() {
    }

    //======

    private String user_email;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    //======

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", passWord='").append(passWord).append('\'');
        sb.append(", user_email='").append(user_email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
