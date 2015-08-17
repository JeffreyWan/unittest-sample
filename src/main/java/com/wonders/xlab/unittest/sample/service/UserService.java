package com.wonders.xlab.unittest.sample.service;

import com.wonders.xlab.unittest.sample.entity.Role;
import com.wonders.xlab.unittest.sample.entity.User;
import com.wonders.xlab.unittest.sample.repository.RoleRepository;
import com.wonders.xlab.unittest.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangqiang on 15/8/17.
 */
@Component
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public void addRolesToUser(long userId, Long... roleIds) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            List<Role> roles = roleRepository.findAll(Arrays.asList(roleIds));
            user.getRoles().addAll(roles);
        }
    }


    public void removeRolesFromUser(long userId, Long... roleIds) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            List<Role> roles = roleRepository.findAll(Arrays.asList(roleIds));
            user.getRoles().removeAll(roles);
        }
    }


}
