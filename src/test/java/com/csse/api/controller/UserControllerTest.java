package com.csse.api.controller;

import com.csse.api.dto.user.UserRequestDTO;
import com.csse.api.dto.user.UserResponseDTO;
import com.csse.api.enums.UserType;
import com.csse.api.exception.UserNotFoundException;
import com.csse.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("test@example.com");
        userRequestDTO.setPassword("password");
        userRequestDTO.setUserType(UserType.ADMIN);

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setUserType(UserType.COLLECTOR);
    }

    @Test
    void testCreateUser() {
        when(userService.createUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = userController.createUser(userRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
        verify(userService, times(1)).createUser(any(UserRequestDTO.class));
    }

    @Test
    void testGetUserByIdFound() {
        when(userService.getUserById(anyLong())).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userService.getUserById(anyLong())).thenThrow(new UserNotFoundException("User not found with id: 1"));

        ResponseEntity<UserResponseDTO> response = userController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }
}
