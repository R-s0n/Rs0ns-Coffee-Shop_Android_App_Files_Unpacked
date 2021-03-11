package com.rson.rsoncoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGiftCardActivity extends AppCompatActivity {

    private EditText etCardNumber, etSecurityCode;
    private Long loggedUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift_card);

        etCardNumber = findViewById(R.id.etGiftCardNumber);
        etSecurityCode = findViewById(R.id.etSecurityCode);

        findViewById(R.id.addGiftCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGiftCard();
            }
        });

        SessionManagement sessionManagement = new SessionManagement(AddGiftCardActivity.this);
        Long userId = sessionManagement.getSession();
        if (userId == -1){
            Intent intent = new Intent(AddGiftCardActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        this.loggedUserId = userId;
    }

    private void addGiftCard() {

        String giftCardNumberString = etCardNumber.getText().toString().trim();
        String giftCardSecCodeString = etSecurityCode.getText().toString().trim();

        if (giftCardNumberString.isEmpty()) {
            etCardNumber.setError("Gift Card Number is required");
            etCardNumber.requestFocus();
            return;
        } else if (giftCardSecCodeString.isEmpty()) {
            etSecurityCode.setError("Security Code is required");
            etSecurityCode.requestFocus();
            return;
        }

        Integer giftCardNumber = Integer.parseInt(giftCardNumberString);
        Integer giftCardSecCode = Integer.parseInt(giftCardSecCodeString);

        GiftCard newCard = new GiftCard();

        newCard.setCardNumber(giftCardNumber);
        newCard.setSecurityCode(giftCardSecCode);
        newCard.setUserId(loggedUserId);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .addGiftCard(newCard);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";

                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("FAILURE")){
                    Toast.makeText(AddGiftCardActivity.this, "Gift Card was not found in our database.  Please check the card number and security code, then try again.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddGiftCardActivity.this, s, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddGiftCardActivity.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddGiftCardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void giftCards(View view) {
    }

    public void logOut(View view) {
        SessionManagement sessionManagement = new SessionManagement(AddGiftCardActivity.this);
        sessionManagement.removeSession();
        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(AddGiftCardActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}