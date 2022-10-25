package com.dani.appudemyspring.io.repository;

import com.dani.appudemyspring.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    UserEntity findById(long id);
}
