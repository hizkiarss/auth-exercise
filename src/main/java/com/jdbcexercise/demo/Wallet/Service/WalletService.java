package com.jdbcexercise.demo.Wallet.Service;

import com.jdbcexercise.demo.Wallet.Entity.WalletEntity;

import java.util.List;

public interface WalletService {
    List<WalletEntity> findAllWalletsByUserId(Long userId);
    WalletEntity addWallet (WalletEntity walletEntity);
}
