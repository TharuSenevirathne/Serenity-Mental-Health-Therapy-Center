package org.example.orm_courseworks.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class User {
    private String userId;
    private String userName;
    private String password;
    private String userRole;
}
