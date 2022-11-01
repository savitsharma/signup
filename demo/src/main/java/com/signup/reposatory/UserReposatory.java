package com.signup.reposatory;

import com.signup.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface UserReposatory extends JpaRepository<User,Integer> {
     public User findByEmailId(String emailId);
     public User findOneByIgnoreCaseEmailIdAndPassword(String emailId, String password);
}
