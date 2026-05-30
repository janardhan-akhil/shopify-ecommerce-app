package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.UserService;
import com.example.utils.ApiResponse;
import com.example.utils.AppConstants;
import com.example.utils.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> saveUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(ApiResponse.success(userService.saveUser(userDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(userService.findUserById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse>> getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        return ResponseEntity.ok(ApiResponse.success(userService.findAllUsers(pageNo, pageSize, sortBy, sortDir)));
    }


}
