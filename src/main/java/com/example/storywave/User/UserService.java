package com.example.storywave.User;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = Logger.getLogger(UserService.class.getName());
    List<User> users = new ArrayList<User>();
    private Long nextUserPkId = 1L;



    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToUserEntity(userDTO);
        //user.setId(nextUserPkId++);
        user.setCreatedAt(LocalDateTime.now());
        users.add(user);
        return convertToUserDTO(user);
    }

    private static User convertToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setUserId(userDTO.getUserId());
        user.setPassword(userDTO.getPassword());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        /*if (boardPostDto.getComments() != null) {
            //데이터ㅓ가 있다는 것
            boardPostDto.getComments().forEach(commentDto -> {
                Comment comment = convertToCommentEntity(commentDto);
                comment.setBoardPost(boardPost);
                boardPost.addComment(comment);
            });
        }*/
        return user;
    }


    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setUserId(user.getUserId());
        userDTO.setPassword(user.getPassword());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());


        /*if (boardPost.getComments() != null) {
            boardPostDto.setComments(
                    boardPost.getComments().stream().map(BoardPostService::convertToCommentDto)
                            .collect(Collectors.toList())
            );
        }*/
        return userDTO;
    }


    public List<UserDTO> getAllUsers() {
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }
    public UserDTO getUserDTOById(Long id) {
        return users.stream()
                .filter(p -> p.getId().equals(id))
                .map(this::convertToUserDTO)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public void deleteUser(Long id) {
        User user = findUserById(id);
        users.remove(user);
        log.info("삭제 성공하였습니다.");
    }

    private User findUserById(Long id) {
        return users.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 글을 찾을 수 없습니다."));
    }

    public UserDTO updateUser(Long id, UserDTO updateUserDTO) {
        User user = findUserById(id);
        user.setPassword(updateUserDTO.getPassword());
        user.setNickname(updateUserDTO.getNickname());
        user.setUpdatedAt(LocalDateTime.now());
        return convertToUserDTO(user);
    }


    public UserDTO updateUserStatus(Long id, UserDTO updateUserStatusDTO) {
        User user = findUserById(id);
        ActiveStatus newStatus = updateUserStatusDTO.getActiveStatus();
        user.setActiveStatus(newStatus);
        return convertToUserDTO(user);
    }

    /*public CommentDto createComment(Long postId, CommentDto createCommentDto) {
        BoardPost boardPost = findBoardPostById(postId);
        Comment comment = convertToCommentEntity(createCommentDto); //두개 다 엔티티
        comment.setId(nextCommentId++);
        comment.setCreatedAt(LocalDateTime.now());
        boardPost.addComment(comment);
        return convertToCommentDto(comment);
    }*/

    /*public void deleteComment(Long postId, Long commentId) {
        BoardPost boardPost = findBoardPostById(postId);
        Comment comment = findCommentById(commentId, boardPost);

        boardPost.removeComment(comment);

    }*/

    /*private static Comment findCommentById(Long commentId, BoardPost boardPost) {
        return boardPost.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("댓글을 찾을 수 없습니다."));
    }
}*/

}
