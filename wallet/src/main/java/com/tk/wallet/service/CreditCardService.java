package com.tk.wallet.service;

import com.tk.wallet.model.CreditCard;
import com.tk.wallet.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    public CreditCard getCreditCardById(Long id) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);
        return optionalCreditCard.orElse(null);
    }

    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public CreditCard updateCreditCard(Long id, CreditCard creditCardDetails) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);
        if (optionalCreditCard.isPresent()) {
            CreditCard existingCreditCard = optionalCreditCard.get();
            existingCreditCard.setCardNumber(creditCardDetails.getCardNumber());
            existingCreditCard.setExpirationDate(creditCardDetails.getExpirationDate());
            existingCreditCard.setCvv(creditCardDetails.getCvv());
            existingCreditCard.setCardholderName(creditCardDetails.getCardholderName());
            existingCreditCard.setCardholderSurname(creditCardDetails.getCardholderSurname());
            return creditCardRepository.save(existingCreditCard);
        } else {
            return null;
        }
    }

    public void deleteCreditCard(Long id) {
        creditCardRepository.deleteById(id);
    }

    public boolean isCreditCardExists(String cardNumber) {
        return creditCardRepository.existsByCardNumber(cardNumber);
    }
}
