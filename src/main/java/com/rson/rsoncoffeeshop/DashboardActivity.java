package com.rson.rsoncoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void logOut(View view) {
        SessionManagement sessionManagement = new SessionManagement(DashboardActivity.this);
        sessionManagement.removeSession();
        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void giftCards(View view) {
        Intent intent = new Intent(DashboardActivity.this, ViewGiftCardsActivity.class);
        startActivity(intent);
    }

    public void placeOrder(View view) {
        Intent intent = new Intent(DashboardActivity.this, OrderCoffeeActivity.class);
        startActivity(intent);
    }
}