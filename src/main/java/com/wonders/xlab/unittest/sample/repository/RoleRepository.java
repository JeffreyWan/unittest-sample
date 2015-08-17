package com.wonders.xlab.unittest.sample.repository;

import com.wonders.xlab.unittest.sample.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangqiang on 15/8/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}

