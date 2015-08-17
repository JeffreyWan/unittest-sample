package com.wonders.xlab.unittest.sample.repository;

import com.wonders.xlab.unittest.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangqiang on 15/8/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}

