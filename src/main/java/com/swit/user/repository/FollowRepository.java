package com.swit.user.repository;

import com.swit.user.entity.Follow;
import com.swit.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findFollowByFromUserAndToUser(User from, User to);

    ArrayList<Follow> findByFromUser(User from);
    ArrayList<Follow> findByToUser(User to);
}
