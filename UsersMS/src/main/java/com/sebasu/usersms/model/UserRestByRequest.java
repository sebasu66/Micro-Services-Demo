package com.sebasu.usersms.model;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
/**
 * Class that represents the request body for the user creation
 */
public class UserRestByRequest extends BaseModelEntity {
    @NotNull
    private String userName;
    @Size (min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    private String name;
    private String lastName;

}
