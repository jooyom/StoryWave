package com.example.storywave.User;

import lombok.*;

import javax.management.relation.Role;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private Role role;
    private String activeStatus;
    private String banReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    /*public ActiveStatus getActiveStatus() {

        return ActiveStatus.valueOf(activeStatus.toString());
    }*/

    public LocalDateTime createdAt(){
        return LocalDateTime.now();
    }

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setUserId(user.getUserId());
        dto.setPassword(user.getPassword());
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail());
        dto.setActiveStatus(user.getActiveStatus());
        dto.setBanReason(user.getBanReason());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setNickname(this.nickname);
        user.setEmail(this.email);
        user.setCreatedAt(this.createdAt);
        user.setActiveStatus(this.activeStatus);
        user.setBanReason(this.banReason);
        user.setUpdatedAt(this.updatedAt);

        return user;
    }


}
