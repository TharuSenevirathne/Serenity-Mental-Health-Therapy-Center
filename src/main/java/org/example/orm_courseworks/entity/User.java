package org.example.orm_courseworks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")

public class User implements SuperEntity {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String userName;
    private String password;
    private String userRole;

}

