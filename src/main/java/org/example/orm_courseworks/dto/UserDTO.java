package org.example.orm_courseworks.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String userRole;

}
