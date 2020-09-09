package com.circle.usermanagementservice.dao;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.model.User;

import java.util.List;

public interface UserDaoCustom
{
    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName );

    List< User > searchUser( UserSearchBean userSearchBean );
}
