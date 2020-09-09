package com.circle.usermanagementservice.business;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.dao.UserDao;
import com.circle.usermanagementservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBusiness implements IUserBusiness
{
    @Autowired
    UserDao userDao;

    public int saveUser( User user )
    {
        userDao.save( user );
        return user.getUserId();
    }

    @Override
    public List<User> getAllUsers()
    {
        List< User > users = userDao.findAll();
        if( users == null )
        {
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public List<User> searchUsers(UserSearchBean userSearchBean) {
        List< User> searchResult = userDao.findUsersByFirstNameAndLastName( userSearchBean.getFirstName(), userSearchBean.getLastName() );
        return searchResult;
    }

}
