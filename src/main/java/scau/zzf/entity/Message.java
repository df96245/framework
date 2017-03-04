package scau.zzf.entity;


import scau.zzf.base.common.Unique;

import java.util.Date;

/**
 * Created by AutoSSM.
 */
public class Message extends Unique {

    private String username;

    private String email;

    private Date createTime;

    private String userId;


    public Message() {
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
