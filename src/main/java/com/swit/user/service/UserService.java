package com.swit.user.service;

import com.swit.user.dto.ProfileDto;
import com.swit.user.entity.Follow;
import com.swit.user.entity.User;
import com.swit.user.repository.FollowRepository;
import com.swit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*
 - saveFollow : 팔로워 추가
 - getFollowIdByFromEmailToId : 팔로우 한 사람과 팔로우 당한 사람의 아이디로 DB 내의 팔로우 아이디 반환 -> 삭제를 위해
 - getFollow : 해당 유저가 팔로우하고 있는 유저 리스트 반환
 - getFollower : 해당 유저를 팔로우하고 있는 유저 리스트 반환
 - isFollowing : 해당 유저가 특정 유저를 팔로우 하고 있는지 여부 반환
 - updateUser : 유저 정보 수정
 - saveUser : 유저 추가
 - findUser : 이메일로 유저를 찾아 반환
 - getProfile : 유저 프로필 반환
 */

@RequiredArgsConstructor
@Service
public class UserService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public Follow saveFollow(String email, String toUserEmail) {
        User fromUser = findUser(email);
        User toUser = findUser(toUserEmail);
        return followRepository.save(Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build());
    }

    public long getFollowIdByFromEmailToId(String email, String toUserEmail) {
        User fromUser = findUser(email);
        User toUser = findUser(toUserEmail);

        Follow follow = followRepository.findFollowByFromUserAndToUser(fromUser, toUser);
        if (follow != null) return follow.getId();
        else return -1;
    }

    public List<Follow> getFollow(String email) {
        User fromUser = findUser(email);
        return followRepository.findByFromUser(fromUser);
    }

    public List<Follow> getFollower(String email) {
        User toUser = findUser(email);
        return followRepository.findByToUser(toUser);
    }

    public boolean isFollowing(String targetEmail, String loginEmail) {
        User fromUser = findUser(loginEmail);
        User toUser = findUser(targetEmail);
        return followRepository.findFollowByFromUserAndToUser(fromUser, toUser) != null;
    }

    public User updateUser (User user) {
        User findUser = findUser(user.getEmail());

        // 부트캠프 변경은 고민중!
        Optional.ofNullable(user.getName())
                .ifPresent(name -> findUser.setName(name));
        // 회원 탈퇴 시 사용
        Optional.ofNullable(user.getStatus())
                .ifPresent(status -> findUser.setStatus(status));

        return userRepository.save(findUser);
    }

    public User findUser(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public User saveUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("이미 가입된 이메일 입니다.");
        }

        if (userRepository.findByPhone(user.getPhone()).isPresent()){
            throw new RuntimeException("이미 가입된 번호 입니다.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public ProfileDto getProfile(String targetEmail, String loginUserEmail){
        User user = findUser(targetEmail);

        // 해당 유저의 팔로워 수
        int follow = getFollow(targetEmail).size();
        int follower = getFollower(targetEmail).size();

        boolean loginUser = (Objects.equals(loginUserEmail, targetEmail));
        boolean following = isFollowing(targetEmail, loginUserEmail);

        ProfileDto profileDto = new ProfileDto(
                user.getEmail(),
                user.getName(),
                user.getBootcamp(),
                following, loginUser,
                follow,
                follower
        );

        return profileDto;
    }
}
