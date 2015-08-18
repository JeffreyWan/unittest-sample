package com.wonders.xlab.unittest.sample.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.wonders.xlab.unittest.sample.test.SpringTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangqiang on 15/8/17.
 */
@DatabaseSetup("UserServiceTest-DataSet.xml")
public class UserServiceTest extends SpringTestCase {

    @Autowired
    private UserService userService;

    @Test
    @ExpectedDatabase(value = "UserServiceTest-testAddRolesToUser-result.xml", table = "sec_user_role",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testAddRolesToUser() throws Exception {
        userService.addRolesToUser(1, 1L, 3L, 4L, 5L);
    }

    @Test
    public void testRemoveRolesFromUser() throws Exception {
        userService.removeRolesFromUser(1, 3L, 4L, 5L);
    }

}