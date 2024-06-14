package com.jdbcexercise.demo.Wallet.Service;

import com.jdbcexercise.demo.User.Entity.UserEntity;
import com.jdbcexercise.demo.Wallet.Entity.WalletEntity;
import com.jdbcexercise.demo.Wallet.Repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImplementation implements WalletService{
    private final WalletRepository walletRepository;

    public WalletServiceImplementation (WalletRepository walletRepository){
        this.walletRepository= walletRepository;
    }

    @Override
    public List<WalletEntity> findAllWalletsByUserId(Long userId) {
        return walletRepository.findByUserIdId(userId);
    }

    @Override
    public WalletEntity addWallet(WalletEntity walletEntity) {
        return walletRepository.save(walletEntity);
    }
}
