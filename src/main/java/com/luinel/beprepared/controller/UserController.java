package com.luinel.beprepared.controller;

import com.luinel.beprepared.dto.request.UserRequestDto;
import com.luinel.beprepared.dto.response.StatsResponse;
import com.luinel.beprepared.dto.response.UserResponseDto;
import com.luinel.beprepared.mapper.Mapper;
import com.luinel.beprepared.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "5. User Controller")
public class UserController {

    private final Mapper mapper;
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.creaeteUser(
                mapper.mapUserRequestToModel(userRequestDto)),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return  ResponseEntity.ok(mapper.mapUserToResponseDto(
                userService.getUserById(id)));
    }

    @GetMapping("/metrics")
    public ResponseEntity<StatsResponse> getMetrics() {
        return  ResponseEntity.ok(userService.getAllStats());
    }
}
