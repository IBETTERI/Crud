package com.better.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos  extends CrudRepository<User, Long> {
}
