package com.anthony.user.dto;

import com.anthony.config.SystemConfigParameter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
public class UserDTO implements Serializable {

    UserDTO()
    {
        setActiveTime(new Date());
        setTimeOut(SystemConfigParameter.getInstance().getTokenExpireTime());
    }

    private String userName;
    private String password;
    private String userType;
    private Date activeTime;
    private int timeOut;
    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", activeTime=" + activeTime +
                ", timeOut=" + timeOut +
                ", token='" + token + '\'' +
                '}';
    }
}
