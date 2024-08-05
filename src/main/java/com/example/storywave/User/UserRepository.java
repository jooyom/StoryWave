package com.example.storywave.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);


    /*Page<User> findAllByNicknameContains(String nickname, PageRequest pageRequest);*/


}