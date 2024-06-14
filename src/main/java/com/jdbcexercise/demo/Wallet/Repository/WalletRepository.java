package com.jdbcexercise.demo.Wallet.Repository;

import com.jdbcexercise.demo.User.Entity.UserEntity;
import com.jdbcexercise.demo.Wallet.Entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    List<WalletEntity> findByUserIdId (Long userId);
}
