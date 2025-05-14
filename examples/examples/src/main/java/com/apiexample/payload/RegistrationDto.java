package com.apiexample.payload;

import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDto {
    private Long id;
    @NotNull
    @Size(min = 2, max = 150, message = "name must be between 2 and 150 characters long")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 10, max = 10 , message = "mobile must be exactly 10 digits long")
    private String mobile;
    private String message;
}