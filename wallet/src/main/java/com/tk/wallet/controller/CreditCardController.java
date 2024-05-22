package com.tk.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tk.wallet.model.AppUser;
import com.tk.wallet.model.CreditCard;
import com.tk.wallet.model.Wallet;
import com.tk.wallet.service.CreditCardService;

@Controller
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/createcreditcard")
    public String createCreditCardForm(Model model) {
        model.addAttribute("creditCard", new CreditCard());
        return "credit-card/creditcard_form";
    }

    @PostMapping("/saveCreditCard")
    public String saveCreditCard(@ModelAttribute CreditCard creditCard, Model model) {
        if (creditCardService.isCreditCardExists(creditCard.getCardNumber())) {
            model.addAttribute("error", "This credit card is already in use.");
            return "credit-card/creditcard_form"; 
        }
        model.addAttribute("creditCard", creditCard);
        creditCardService.saveCreditCard(creditCard);
        return "credit-card/successmessage";
    }
}