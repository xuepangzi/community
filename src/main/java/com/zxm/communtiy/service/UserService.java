package com.zxm.communtiy.service;

import com.zxm.communtiy.mapper.UserMapper;
import com.zxm.communtiy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        user.setTmgCreate(System.currentTimeMillis());
        user.setTmgModified(user.getTmgCreate());
        User dbUser = userMapper.findByAccoundId(user.getAccoundId());
        if(dbUser == null){
            userMapper.insert(user);
        }else{
            userMapper.updateByAccoundId(user);
        }
    }
}
