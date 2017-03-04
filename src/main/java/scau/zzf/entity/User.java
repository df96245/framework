package scau.zzf.entity;


import scau.zzf.base.common.Unique;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by AutoSSM.
 */
@Table(name = "users")
public class User extends Unique {

    private String username;

    private String password;

    private String salt;

//    private Date createTime;

    @Transient
    private Message message;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }

    public String getCredentialsSalt() {
        return username+salt;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
