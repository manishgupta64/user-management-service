package com.circle.usermanagementservice.dao;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.model.User;

import java.util.List;
import java.util.Map;

public interface UserDaoCustom
{
    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName );

    List< User > searchUser( UserSearchBean userSearchBean );

    public Map<String, Integer> countUsersByBloodGroup();
}
