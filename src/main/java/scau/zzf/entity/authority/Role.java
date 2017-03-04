package scau.zzf.entity.authority;

import scau.zzf.base.common.Unique;

import javax.persistence.Table;

/**
 * Created by zzf on 2017/2/26.
 */
@Table(name = "roles")
public class Role extends Unique{
    private String name;
    private String content;


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
