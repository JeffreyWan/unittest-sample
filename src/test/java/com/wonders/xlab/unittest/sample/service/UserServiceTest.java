package com.wonders.xlab.unittest.sample.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
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
    @DatabaseSetup("users-dataset.xml")
    public void testAddRolesToUser() throws Exception {
        System.out.println("userService = " + userService);
    }

    @Test
    public void testRemoveRolesFromUser() throws Exception {

    }
}