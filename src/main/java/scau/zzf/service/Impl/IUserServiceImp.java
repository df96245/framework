package scau.zzf.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scau.zzf.base.common.IBaseMapper;
import scau.zzf.base.common.IBaseService;
import scau.zzf.entity.Message;
import scau.zzf.entity.User;
import scau.zzf.entity.authority.MessageRole;
import scau.zzf.entity.authority.Role;
import scau.zzf.service.IUserService;

/**
 * Created by AutoSSM.
 */
@Service
public class IUserServiceImp extends AbstractBaseService<User> implements IUserService {

    @Autowired
    private IBaseService<User> userIBaseService;
    @Autowired
    private IBaseService<Message> messageIBaseService;
    @Autowired
    private IBaseService<Role> roleIBaseService;
    @Autowired
    private IBaseService<MessageRole> messageRoleIBaseService;
    @Override
    public void add(User user, Message message) {
        userIBaseService.add(user);
        message.setUserId(user.getId());
        messageIBaseService.add(message);
        Role role = new Role("USER");
        role = roleIBaseService.findOne(role);
        //为每个用户生成角色USER
        MessageRole messageRole = new MessageRole(user.getUsername(), role.getId(), role.getName());
        messageRoleIBaseService.add(messageRole);
    }
}
