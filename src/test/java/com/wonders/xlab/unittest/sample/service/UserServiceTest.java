package com.wonders.xlab.unittest.sample.service;

import com.wonders.xlab.unittest.sample.test.SpringTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangqiang on 15/8/17.
 */
public class UserServiceTest extends SpringTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testAddRolesToUser() throws Exception {
        System.out.println("userService = " + userService);
    }

    @Test
    public void testRemoveRolesFromUser() throws Exception {

    }
}