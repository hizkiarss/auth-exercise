package com.jdbcexercise.demo.User.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;

import java.time.Instant;

@Data
@Entity
@Table(name = "user", schema = "montrack")
public class UserEntity {

   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
   @SequenceGenerator(name= "user_id_gen", sequenceName = "montrack.user_id_seq", allocationSize = 1)
   @Column (name = "id",  nullable = false )
    private Long id;

    @NotNull
    @Column(name = "name" , nullable = false )
    private String name;

    @NotNull
    @Column (name = "email" , nullable = false)
    private String email;

    @Column (name = "password_hash" , nullable = false)
    private String passwordHash;

    @Column (name = "password_salt" , nullable = false)
    private String passwordSalt;

    @Column (name = "hash_pin" , nullable = false)
    private String hashPin;

    @Column (name = "salt_pin" , nullable = false)
    private String saltPin;

    @Column (name = "profile_pic", nullable = false)
    private String profilePic;

    @NotNull
    @Column (name ="created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column (name ="updated_at",  nullable = false)
    private Instant updatedAt;

    @Column (name = "deleted_at" )
    private Instant deletedAt;

    @PrePersist
 protected void onCreate(){
     createdAt= Instant.now();
     updatedAt= Instant.now();
    }

    @PreUpdate
 protected void onUpdate(){updatedAt = Instant.now();}
}
