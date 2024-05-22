package com.tk.wallet.service;

import com.tk.wallet.model.AppUser;
import com.tk.wallet.model.Wallet;
import com.tk.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public AppUser saveUser(AppUser appUser) {
        // Create default wallets for new user
        List<Wallet> wallets = new ArrayList<>();
        wallets.add(new Wallet("USD", 0.0, appUser)); // USD cüzdanı için başlangıç bakiyesi 0.0
        wallets.add(new Wallet("EUR", 0.0, appUser)); // EUR cüzdanı için başlangıç bakiyesi 0.0
        wallets.add(new Wallet("GBP", 0.0, appUser)); // GBP cüzdanı için başlangıç bakiyesi 0.0
        wallets.add(new Wallet("TRY", 0.0, appUser)); // TRY cüzdanı için başlangıç bakiyesi 0.0

        appUser.setWallets(wallets);
        return userRepository.save(appUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isUserExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
