package com.jdbcexercise.demo.Wallet.Entity;


import com.jdbcexercise.demo.User.Entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table (name = "wallet" , schema = "montrack")
public class WalletEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "wallet_id_gen")
    @SequenceGenerator(name= "wallet_id_gen", sequenceName = "montrack.wallet_id_seq", allocationSize = 1)
    @Column (name = "id",  nullable = false )
    private Integer id;


    @Column(name = "title")
    private String title;

    @PositiveOrZero(message = "The entered amount is error. Please re-type the correct amount" )
    @Column (name ="balance")
    private Integer balance;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private UserEntity userId;

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
