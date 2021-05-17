package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>  {

    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
}
