package com.example.service.impl;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public UserDto saveUser(UserDto userDto) {
        return mapToDto(userRepository.save(mapToEntity(userDto)));
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return mapToDto(user);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return mapToDto(user);
        } else {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return mapToDto(user);
        } else {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public PageResponse findAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<User> users = userRepository.findAll(pageable);
        List<User> content = users.getContent();
        List<UserDto> userDtos = content.stream().map(this::mapToDto).collect(Collectors.toList());
        return PageResponse.builder()
                .content(userDtos)
                .pageNo(users.getNumber())
                .pageSize(users.getSize())
                .totalElements(users.getTotalElements())
                .totalPages(users.getTotalPages())
                .last(users.isLast())
                .build();

    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        userRepository.deleteById(id);

    }

    public UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    public User mapToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }
}
