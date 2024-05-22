package com.tk.wallet.repository;

import com.tk.wallet.model.AppUser;
import com.tk.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByOwnerAndCurrency(AppUser owner, String currency);
    Wallet findByOwnerAndCurrency(AppUser owner, String currency);
}
