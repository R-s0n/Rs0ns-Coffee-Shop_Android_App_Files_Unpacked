package com.rson.rsoncoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class OrderCoffeeActivity extends AppCompatActivity {

    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);

        this.cart = new Cart();
    }

    public void giftCards(View view) {
    }

    public void addGiftCard(View view) {
    }

    public void logOut(View view) {
    }

    public void addSeasonal(View view) {
    }

    public void addColdBrew(View view) {
    }

    public void addEspresso(View view) {
    }

    public void addMacchiato(View view) {
    }

    public void addMocha(View view) {
    }

    public void addCappuccino(View view) {
    }

    public void addLatte(View view) {
    }

    public void addAmericano(View view) {
    }

    public void addPourOverCoffee(View view) {
    }
}