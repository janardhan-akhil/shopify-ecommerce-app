package com.example.service;

import com.example.dto.UserDto;
import com.example.utils.PageResponse;

import java.util.List;

public interface UserService {

    public UserDto saveUser(UserDto userDto);
    public UserDto findUserById(Long id);
    public UserDto findUserByUsername(String username);
    public UserDto findUserByEmail(String email);
    public PageResponse findAllUsers(int page, int pageSize, String sortBy, String sortDir);
    public void deleteUserById(Long id);
}
