package com.rson.rsoncoffeeshop;

import java.util.Date;

public class GiftCard {
    private Long id;
    private Integer cardNumber;
    private Integer securityCode;
    private Integer value;
    private Long userId;
    private User owner;
    private Date createdAt;


    public GiftCard(){}

    public GiftCard(Integer cardNumber, Integer value, Integer securityCode,User owner){
        this.cardNumber = cardNumber;
        this.value = value;
        this.securityCode = securityCode;
        this.owner = owner;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
}
