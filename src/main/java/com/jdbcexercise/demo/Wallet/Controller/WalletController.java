package com.jdbcexercise.demo.Wallet.Controller;

import com.jdbcexercise.demo.Wallet.Entity.WalletEntity;
import com.jdbcexercise.demo.Wallet.Service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public WalletEntity addWallet (@RequestBody WalletEntity walletEntity){
        return walletService.addWallet(walletEntity);
    }

    @GetMapping("/{userId}")
    public List<WalletEntity> getAllWalletByUserId (@PathVariable Long userId){
        return walletService.findAllWalletsByUserId(userId);
    }
}
