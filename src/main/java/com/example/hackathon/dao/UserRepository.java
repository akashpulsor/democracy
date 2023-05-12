package com.example.hackathon.dao;

import com.example.hackathon.model.Event;
import com.example.hackathon.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {


    @Query(nativeQuery=true, value="SELECT email FROM User WHERE email like '?1%'")
    List<User> getUsersByEmail(String email);

    List<User> findByEmailStartsWith(String email);


    List<User> findByUserNameStartsWith( String userName);

    @Query(nativeQuery=true, value="SELECT  *  FROM  User  u WHERE u.user_name LIKE CONCAT(:userName,'%')")
    List<User> finaProfiles(String userName);
    User save(User user);

    Optional<User> findByUserName(String userName);

    Optional<User> findById(long id);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);


}
