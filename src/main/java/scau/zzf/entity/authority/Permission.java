package scau.zzf.entity.authority;

import scau.zzf.base.common.Unique;

/**
 * Created by zzf on 2017/2/26.
 */
public class Permission extends Unique {
    private String name;
    private String content;


    public Permission() {
    }

    public Permission(String name) {
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
