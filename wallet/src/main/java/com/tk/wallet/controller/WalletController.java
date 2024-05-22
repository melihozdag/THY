package com.tk.wallet.controller;

import com.tk.wallet.model.AppUser;
import com.tk.wallet.model.Wallet;
import com.tk.wallet.service.UserService;
import com.tk.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @GetMapping("/viewwallets")
    public String viewWallets(Model model) {
        List<Wallet> wallets = walletService.getAllWallets();
        model.addAttribute("wallets", wallets);
        return "wallet/wallet_list"; // Dizin yolunu ekledik
    }

    @GetMapping("/createwallet")
    public String createWalletForm(Model model) {
        List<AppUser> users = userService.getAllUsers();
        model.addAttribute("wallets", new Wallet());
        model.addAttribute("users", users);
        return "wallet/wallet_form"; // Dizin yolunu ekledik
    }

    @PostMapping("/api/wallets")
    public String createWallet(Model model, @ModelAttribute Wallet wallet) {
        Wallet existingWallet = walletService.findByOwnerAndCurrency(wallet.getOwner(), wallet.getCurrency());
        if (existingWallet != null) {
            existingWallet.setBalance(existingWallet.getBalance() + wallet.getBalance());
            walletService.saveWallet(existingWallet);
        } else {
            walletService.saveWallet(wallet);
        }
        // if (walletService.existsByOwnerAndCurrency(wallet.getOwner(),
        // wallet.getCurrency())) {
        // List<AppUser> users = userService.getAllUsers();
        // model.addAttribute("errorMessage", "You already have a wallet with this
        // currency.");
        // model.addAttribute("wallets", wallet);
        // model.addAttribute("users", users);
        // return "wallet/wallet_form"; // Form sayfasına geri dön
        // }

        // walletService.saveWallet(wallet);
        return "credit-card/creditcard_option";
    }

    @GetMapping("/wallet/edit/{id}")
    public String editWalletForm(@PathVariable Long id, Model model) {
        List<AppUser> users = userService.getAllUsers();
        Wallet wallet = walletService.getWalletById(id);
        walletService.deleteWallet(id);
        model.addAttribute("wallets", wallet);
        model.addAttribute("users", users);
        return "wallet/wallet_form";
    }

    @GetMapping("/wallet/delete/{id}")
    public String deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return "redirect:/viewwallets";
    }

    @GetMapping("/wallet/withdraw/{id}")
    public String withdrawForm(Model model, @PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);

        model.addAttribute("wallet", wallet);
        return "wallet/withdraw_form";
    }

    @PostMapping("/wallet/withdraw")
    public String withdrawWallet(@RequestParam("id") Long id, @RequestParam("amount") double amount,
            Model model) {
        Wallet wallet = walletService.getWalletById(id);
        if (wallet != null) {
            if (wallet.getBalance() >= amount) {
                walletService.withdraw(wallet, amount);
                model.addAttribute("successMessage", "Withdrawal successful.");
            } else {
                model.addAttribute("errorMessage", "Insufficient balance.");
            }
        } else {
            model.addAttribute("errorMessage", "Wallet not found.");
        }
        model.addAttribute("wallet", wallet);
        return "redirect:/viewwallets";
    }

}
