package scau.zzf.base.common;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by zzf on 2016/11/21.
 */
public class Unique implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select replace(uuid(), '-', '')")
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
