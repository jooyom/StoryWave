package com.example.storywave.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;
    List<User> users = new ArrayList<User>();
    private Long nextUserPkId = 1L;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto createUser(UserDto userDto) {
        User user = convertToUserEntity(userDto);
        user.setId(nextUserPkId++);
        user.setCreatedAt(LocalDateTime.now());
        users.add(user);
        return convertToUserDto(user);
    }

    private User convertToUserEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setUserId(userDto.getUserId());
        user.setPassword(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        return user;
    }


    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setUserId(user.getUserId());
        userDto.setPassword(user.getPassword());
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());
        return userDto;
    }


    //service에서 값을 호출해야 controller에 뜬다

    /*public List<UserDto> getAllUsers() {
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }*/
    public UserDto getUserDtoById(Long id) {
        return users.stream()
                .filter(p -> p.getId().equals(id))
                .map(this::convertToUserDto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }


    private User findUserById(Long id) {
        return users.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 글을 찾을 수 없습니다."));
    }

    /*public UserDto updateUser(Long id, UserDto updateUserDto) {
        User user = findUserById(id);
        user.setPassword(updateUserDto.getPassword());
        user.setNickname(updateUserDto.getNickname());
        user.setUpdatedAt(LocalDateTime.now());
        return convertToUserDto(user);
    }*/


   /* public UserDto updateUserStatus(Long id, UserDto updateUserStatusDto) {
        User user = findUserById(id);
        ActiveStatus newStatus = updateUserStatusDto.getActiveStatus();
        user.setActiveStatus(newStatus);
        return convertToUserDto(user);

    }*/

    /*@Transactional(readOnly = true)
    public UserDto getUser(Long id) {
        User user = userRepository.findAll().get(users.size());
        return UserDto.fromEntity(user);
    }*/


    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean updateUserStatus(Long userId, String status, String reason) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setActiveStatus(status);
            user.setBanReason(reason);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }


}
