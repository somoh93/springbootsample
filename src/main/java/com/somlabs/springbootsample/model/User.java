package com.somlabs.springbootsample.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Column(updatable = false, unique = true)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max=20, message="Password length should be 8-20")
    private String password;

    @NotBlank(message="Name cannot be blank")
    private String name;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dateOfBirth;

    private Date created_at;
    private Date modified_at;

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.modified_at = new Date();
    }

}
