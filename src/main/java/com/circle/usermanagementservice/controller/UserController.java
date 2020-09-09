package com.circle.usermanagementservice.controller;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.business.IUserBusiness;
import com.circle.usermanagementservice.exceptions.InvalidArgumentException;
import com.circle.usermanagementservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController
{
    @Autowired
    IUserBusiness userBusiness;

    @PostMapping( value = "/user/info/v1/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity< ? > saveUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response )
    {
        Map< String, String > errors  = new HashMap<>();
        try
        {
            if( user == null )
            {
                errors.put( "message", "user object is null." );
                throw new InvalidArgumentException("user object is null while saving user." + user );
            }
            userBusiness.saveUser( user );
            return ResponseEntity.ok( user );
        }
        catch (Exception e)
        {
            errors.put("error", String.valueOf( true ));
        }
        return ResponseEntity.ok( errors );
    }

    @GetMapping( value = "/user/info/v1/allUser",  produces = "application/json")
    public Map< String, Object > getAllUsers()
    {
        List< User > users = userBusiness.getAllUsers();
        Map< String, Object > data = new HashMap<>();
        data.put( "users", users );
        data.put( "count", users.size() );
        return data;
    }

    @PostMapping( value = "/user/search/v1", consumes = "application/json", produces = "application/json")
    public ResponseEntity< ? > searchUsers(@RequestBody UserSearchBean userSearchBean, HttpServletRequest request, HttpServletResponse response )
    {
        Map< String, String > errors = new HashMap<>();
        try
        {
            if( userSearchBean == null )
            {
                errors.put( "message", "userSearchBean is null." );
                throw new InvalidArgumentException( "Invalid userSearchBean " );
            }
            List< User > users = userBusiness.searchUsers( userSearchBean );
            Map< String, Object > data = new HashMap<>();
            data.put( "success", String.valueOf( true ));
            data.put( "users", users );
            return ResponseEntity.ok( data );
        }
        catch (Exception e)
        {
            errors.put("error", String.valueOf( true ));
            return ResponseEntity.ok( errors );
        }
    }





}
