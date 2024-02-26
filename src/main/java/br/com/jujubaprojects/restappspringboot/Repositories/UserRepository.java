package br.com.jujubaprojects.restappspringboot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jujubaprojects.restappspringboot.Model.User_Permission.User;


public interface UserRepository extends JpaRepository< User,Long >{

     @Query("SELECT u FROM User WHERE u.userName =:usernName")
    User findByUserName(@Param ("userName") String userName);
}
