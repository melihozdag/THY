package com.tk.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }
}
// Kullanıcı Oluşturma (Create User): /createuser URL'sine giderek user_form.html sayfasını görüntüler ve yeni kullanıcı oluşturabilirsiniz.
// Kullanıcı Listeleme (View Users): /viewusers URL'sine giderek user_list.html sayfasını görüntüler ve kullanıcıları listeleyebilirsiniz.
// Cüzdan Oluşturma (Create Wallet): /createwallet URL'sine giderek wallet_form.html sayfasını görüntüler ve yeni cüzdan oluşturabilirsiniz.
// Cüzdan Listeleme (View Wallets): /viewwallets URL'sine giderek wallet_list.html sayfasını görüntüler ve cüzdanları listeleyebilirsiniz.