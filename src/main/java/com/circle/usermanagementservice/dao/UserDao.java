package com.circle.usermanagementservice.dao;

import com.circle.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository< User, Integer >, UserDaoCustom
{

}
