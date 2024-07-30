package com.example.storywave.User;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private Role role;
    private ActiveStatus activeStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt ;


    public ActiveStatus getActiveStatus() {
        return ActiveStatus.valueOf(activeStatus.toString());
    }



    /*public static UserDTO fromEntity(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userId(user.getUserId())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .activeStatus(user.getActiveStatus())
                .build();
    }

    public User toEntity() {
        return new User(
                this.id,
                this.username,
                this.userId,
                this.password,
                this.nickname,
                this.email,
                this.role,
                this.activeStatus,
                this.createdAt,
                this.updatedAt
        );
    }*/
}
