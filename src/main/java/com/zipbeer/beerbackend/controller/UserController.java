package com.zipbeer.beerbackend.controller;

import com.zipbeer.beerbackend.dto.UserDto;
import com.zipbeer.beerbackend.entity.UserEntity;
import com.zipbeer.beerbackend.service.UserService;
import com.zipbeer.beerbackend.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final FileUtil fileUtil;

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<UserEntity> getUserByNickname(@PathVariable String nickname) {
        Optional<UserEntity> userEntity = userService.getUserByNickname(nickname);
        return userEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{nickname}")
    public ResponseEntity<UserEntity> updateUserByNickname(@PathVariable String nickname, @RequestBody UserEntity user) {
        Optional<UserEntity> updatedUser = userService.updateUserByNickname(nickname, user);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/{profileImage}")
    public ResponseEntity<Resource> getImage(@PathVariable("profileImage") String profileImage){
        return fileUtil.getFile(profileImage);
    }
    @PatchMapping("")
    public ResponseEntity<?> modify(UserDto userDto){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        userDto.setUsername(id);
        userService.modify(userDto);
        return ResponseEntity.ok(userDto);
    }

}
