package com.tk.wallet.service;

import com.tk.wallet.model.AppUser;
import com.tk.wallet.model.Wallet;
import com.tk.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.orElse(null);
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet updateWallet(Long id, Wallet updatedWallet) {
        if (!walletRepository.existsById(id)) {
            return null;
        }
        updatedWallet.setId(id);
        return walletRepository.save(updatedWallet);
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    public boolean existsByOwnerAndCurrency(AppUser owner, String currency) {
        return walletRepository.existsByOwnerAndCurrency(owner, currency);
    }

    public Wallet findByOwnerAndCurrency(AppUser owner, String currency) {
        return walletRepository.findByOwnerAndCurrency(owner, currency);
    }
    public void withdraw(Wallet wallet, double amount) {
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }
}
