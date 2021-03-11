package com.rson.rsoncoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewGiftCardsActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gift_cards);

        SessionManagement sessionManagement = new SessionManagement(ViewGiftCardsActivity.this);
        Long userId = sessionManagement.getSession();
        if (userId == -1){
            Intent intent = new Intent(ViewGiftCardsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        User loggedUser = new User();
        loggedUser.setId(userId);

        Call<User> loggedUserCall = RetrofitClient
                .getInstance()
                .getAPI()
                .getLoggedUser(loggedUser);

        loggedUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User thisUser = response.body();
                TextView scrollTitle = findViewById(R.id.scrollTitle);
                String scrollTitleString = scrollTitle.getText().toString();
                scrollTitleString += thisUser.getUserName();
                scrollTitle.setText(scrollTitleString);
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ViewGiftCardsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<GiftCard>> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getGiftCards(loggedUser);

        call.enqueue(new Callback<List<GiftCard>>() {
            @Override
            public void onResponse(Call<List<GiftCard>> call, Response<List<GiftCard>> response) {
                String errorCode = getErrorCode();

                List<GiftCard> giftCards = response.body();
                linearLayout = findViewById(R.id.mainScroll);
                    for (GiftCard g : giftCards) {

                        TextView textViewOne = new TextView(ViewGiftCardsActivity.this);
                        String rowOne = "Card Number: " + g.getCardNumber().toString() + "                                 Value: $" + g.getValue().toString() + ".00";
                        textViewOne.setText(rowOne);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(15, 15, 15, 15);
                        textViewOne.setLayoutParams(params);
                        linearLayout.addView(textViewOne);

                        TextView textViewTwo = new TextView(ViewGiftCardsActivity.this);
                        DateFormat formatter = new SimpleDateFormat("HH:mm:ss - dd.MM.yyyy");
                        Date dateOfCardAdd = g.getCreatedAt();
                        String rowTwo = "Date Purchased:                        " + formatter.format(dateOfCardAdd);
                        LinearLayout.LayoutParams paramsTwo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        paramsTwo.setMargins(15, 15, 15, 125);
                        textViewTwo.setLayoutParams(paramsTwo);
                        textViewTwo.setText(rowTwo);
                        linearLayout.addView(textViewTwo);

                        if (g.getValue() != 5){
                            Toast.makeText(ViewGiftCardsActivity.this, errorCode, Toast.LENGTH_LONG).show();
                        }
                    }
            }
            @Override
            public void onFailure(Call<List<GiftCard>> call, Throwable t) {
//                Toast.makeText(ViewGiftCardsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getErrorCode() {
        return "[FLAG - 6]";
    }

    public void logOut(View view) {
        SessionManagement sessionManagement = new SessionManagement(ViewGiftCardsActivity.this);
        sessionManagement.removeSession();
        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(ViewGiftCardsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void giftCards(View view) {
    }

    public void addGiftCard(View view) {
        Intent intent = new Intent(ViewGiftCardsActivity.this, AddGiftCardActivity.class);
        startActivity(intent);
    }
}