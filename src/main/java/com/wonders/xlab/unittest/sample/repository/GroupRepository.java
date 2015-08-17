package com.wonders.xlab.unittest.sample.repository;

import com.wonders.xlab.unittest.sample.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangqiang on 15/8/17.
 */
public interface GroupRepository extends JpaRepository<Group, Long> {
}

