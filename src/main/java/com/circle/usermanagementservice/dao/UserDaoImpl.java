package com.circle.usermanagementservice.dao;

import com.circle.usermanagementservice.beans.UserSearchBean;
import com.circle.usermanagementservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDaoCustom
{
    @Autowired
    EntityManager em;

    @Override
    public List<User> findUsersByFirstNameAndLastName(String firstName, String lastName)
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery( User.class );
        Root< User > user = cq.from( User.class );

        List< Predicate > predicates = new ArrayList<>();

        if( firstName != null )
        {
            predicates.add( cb.like( user.get( "firstName"),"%" + firstName + "%" ) );
        }
        if( lastName != null )
        {
            predicates.add( cb.like( user.get("lastName"), "%" + lastName + "%"));
        }
        cq.where( predicates.toArray( new Predicate[0]) );
        List< User> users = em.createQuery( cq ).getResultList();
        return users;
    }

    @Override
    public List<User> searchUser(UserSearchBean userSearchBean)
    {
        if( userSearchBean == null )
        {
            return new ArrayList<>();
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery< User > cq = cb.createQuery( User.class );
        Root< User > root = cq.from( User.class );

        List< Predicate > predicates = new ArrayList<>();
        if( userSearchBean.getFirstName() != null )
        {
            predicates.add( cb.like( root.get("firstName"), userSearchBean.getFirstName() ));
        }

        if( userSearchBean.getLastName() != null )
        {
            predicates.add( cb.like( root.get("lastName"), userSearchBean.getLastName() ));
        }
        if( userSearchBean.getAge() > 0 )
        {
            predicates.add( cb.equal( root.get("dob"), userSearchBean.getDob() ) );
        }
        if( userSearchBean.getMobileNo() != null )
        {
            predicates.add( cb.equal( root.get("mobileNo"), userSearchBean.getMobileNo()));
        }

        cq.where( predicates.toArray( new Predicate[0] ) );
        List<User> users = em.createQuery( cq ).getResultList();
        return users;
    }
}
