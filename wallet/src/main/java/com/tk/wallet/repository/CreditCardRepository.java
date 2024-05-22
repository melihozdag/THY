package com.tk.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tk.wallet.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
    boolean existsByCardNumber(String cardNumber);
}