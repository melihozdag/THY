package com.tk.wallet.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Wallet> wallets;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<CreditCard> creditCards;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    // Diğer alanlar ve getter/setter metotları

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
