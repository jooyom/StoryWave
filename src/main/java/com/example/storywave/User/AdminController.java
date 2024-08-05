package com.example.storywave.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // 사용자 상태 업데이트
    @PutMapping("/{status}/reject")
    public ResponseEntity<String> updateUserStatus(
            @RequestParam Long Id,
            @RequestParam String activeStatus,
            @RequestBody String banReason) {

        User user = userService.findById(Id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setActiveStatus(activeStatus);
        user.setBanReason(banReason);
        userService.save(user);

        return ResponseEntity.ok("User status updated successfully");
    }

    /*@PutMapping("/{Id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("Id") Long Id, @RequestBody UserDto userDto) {
        return userService
                .updateUser(Id, userDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/
}
