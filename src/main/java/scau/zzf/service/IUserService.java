package scau.zzf.service;

import scau.zzf.base.common.IBaseService;
import scau.zzf.entity.Message;
import scau.zzf.entity.User;

/**
 * Created by AutoSSM.
 */
public interface IUserService extends IBaseService<User>{

    void add(User user, Message message);

}
