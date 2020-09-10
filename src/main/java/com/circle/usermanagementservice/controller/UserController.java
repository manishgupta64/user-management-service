package com.circle.usermanagementservice.controller;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.business.IUserBusiness;
import com.circle.usermanagementservice.exceptions.InvalidArgumentException;
import com.circle.usermanagementservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity< ? > saveUser(@RequestBody List< User > users, HttpServletRequest request, HttpServletResponse response )
    {
        Map< String, String > errors  = new HashMap<>();
        try
        {
            if( users == null )
            {
                errors.put( "message", "user object is null." );
                throw new InvalidArgumentException("user object is null while saving user." );
            }
            for( User user : users )
            {
                userBusiness.saveUser( user );
            }
            return ResponseEntity.ok( users );
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    @GetMapping( value = "/user/countByBloodGroup/v1")
    public ResponseEntity< ? > getUsersCountByBloodGroup()
    {
        Map< String, Integer > usersCountByBloodGroup = userBusiness.getUsersCountByBloodGroup();
        Map< String, Object > data = new HashMap<>();
        data.put("sucess", String.valueOf( true ));
        data.put("usersCountByBloodGroup", usersCountByBloodGroup );
        return ResponseEntity.ok( data );
    }

    @DeleteMapping( value = "/user/delete/v1/{userId}")
    public ResponseEntity< ? > deleteUser( @PathVariable Integer userId )
    {
        Map< String, String> errors = new HashMap<>();
        try
        {
            if( userId == null || userId <= 0 )
            {
                errors.put("message", "Invalid userId" + userId );
                throw new InvalidArgumentException("Invalid userId");
            }
            boolean isUserDeleted = userBusiness.deleteUserById( userId );
            Map< String, Object > data = new HashMap<>();
            data.put("success", String.valueOf( true ));
            data.put("isUserDeleted", String.valueOf( isUserDeleted ));
            return ResponseEntity.ok( data );
        }
        catch (InvalidArgumentException e)
        {
            errors.put("error", String.valueOf( true ) );
            return ResponseEntity.ok( errors );
        }
    }


}
