package com.moovie.moovienetwork.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentDto {

    private String email;

    private String premiumType;

    private LocalDate date;

    private Double amount;

    public PaymentDto() {
    }

    public PaymentDto(String email, LocalDate date, String premiumType, Double amount) {
        this.email = email;
        this.date = date;
        this.premiumType = premiumType;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(String premiumType) {
        this.premiumType = premiumType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
