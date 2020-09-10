package com.circle.usermanagementservice.business;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.dao.UserDao;
import com.circle.usermanagementservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        for (User user : users) {
            if( user.getAddress() != null )
            {
                user.getAddress().getAddressId();
            }
        }
        return users;
    }

    @Override
    public List<User> searchUsers(UserSearchBean userSearchBean) {
        List< User> searchResult = userDao.findUsersByFirstNameAndLastName( userSearchBean.getFirstName(), userSearchBean.getLastName() );
        searchResult.stream().forEach( user -> {
            if( user.getAddress() != null )
            {
                user.getAddress().getAddressId();
            }
        });
        return searchResult;
    }

    @Override
    public Map<String, Integer> getUsersCountByBloodGroup()
    {
        return userDao.countUsersByBloodGroup();
    }

    @Override
    public boolean deleteUserById(int userId)
    {
        boolean isUserDeleted = false;
        if( userId > 0 )
        {
            Optional< User > user = userDao.findById( userId );
            if ( user.isPresent() )
            {
                userDao.deleteById( userId );
                isUserDeleted = true;
            }
        }
        return isUserDeleted;
    }
}
