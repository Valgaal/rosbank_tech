package com.example.nikita.rosbank_tech.Models;

public class PaymentResponse {
    private String login;
    private ItemOrder product;
    private CardOwner cardInfo;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ItemOrder getProduct() {
        return product;
    }

    public void setProduct(ItemOrder product) {
        this.product = product;
    }

    public CardOwner getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardOwner cardInfo) {
        this.cardInfo = cardInfo;
    }
}

