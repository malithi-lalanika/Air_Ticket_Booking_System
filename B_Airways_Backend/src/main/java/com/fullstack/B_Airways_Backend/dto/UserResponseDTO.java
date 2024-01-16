package com.fullstack.B_Airways_Backend.dto;

import com.fullstack.B_Airways_Backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
    private boolean success;
    private int status;
    private User data;
}
