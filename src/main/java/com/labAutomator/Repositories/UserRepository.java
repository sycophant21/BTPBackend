package com.labAutomator.Repositories;

import com.labAutomator.domain.User;
import com.labAutomator.domain.id.UserID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, UserID> {

    @Query("SELECT u FROM User u WHERE u.emailID = :emailID")
    User getUserByEmailID(@Param("emailID") String emailID);
}
