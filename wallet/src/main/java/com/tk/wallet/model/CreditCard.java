package com.tk.wallet.model;

import java.time.YearMonth;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    private YearMonth expirationDate;

    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;

    private String cardholderName;
    private String cardholderSurname;

    public CreditCard() {
        // Boş yapıcı metod
    }

    public CreditCard(String cardNumber, YearMonth expirationDate, String cvv, String cardholderName,
                      String cardholderSurname) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardholderName = cardholderName;
        this.cardholderSurname = cardholderSurname;
    }

    // Getter ve setter metodları

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardholderSurname() {
        return cardholderSurname;
    }

    public void setCardholderSurname(String cardholderSurname) {
        this.cardholderSurname = cardholderSurname;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                ", cardholderSurname='" + cardholderSurname + '\'' +
                '}';
    }
}
