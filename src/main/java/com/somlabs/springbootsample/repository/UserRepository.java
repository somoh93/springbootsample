package com.somlabs.springbootsample.repository;

import com.somlabs.springbootsample.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    @Override
    Iterable<User> findAll();
}
