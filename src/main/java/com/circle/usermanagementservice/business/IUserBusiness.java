package com.circle.usermanagementservice.business;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.model.User;

import java.util.List;

public interface IUserBusiness
{
    public int saveUser( User user );

    public List< User > getAllUsers();

    public List< User > searchUsers(UserSearchBean userSearchBean );
}
