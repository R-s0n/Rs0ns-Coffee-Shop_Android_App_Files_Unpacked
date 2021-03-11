package com.rson.rsoncoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        findViewById(R.id.tvRegisterLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        Long userId = sessionManagement.getSession();
        if (userId != -1){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void loginUser() {
        final String userName = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (userName.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .checkUser(new User(userName, password));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                Integer a = 19;
                Integer b = 23;
                Integer c = 78;
                Integer d = 9;
                Integer h = 51;
                String f = String.valueOf((a * b - c));
                String g = String.valueOf((c * d + h));
                Long retrofitAnalyticsOne = retrofitAnalytics(f);
                Long retrofitAnalyticsTwo = retrofitAnalytics(g);
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    String[] tempArr = s.split(",");
                    String thisUsername = tempArr[0];
                    Long q = Long.parseLong(tempArr[1]);

                if (thisUsername.equals(userName)) {
                    User loggedUser = new User(thisUsername, q);
                    SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                    sessionManagement.saveSession(loggedUser);
                    if (q == retrofitAnalyticsOne) {
                        Toast.makeText(LoginActivity.this, tempArr[2], Toast.LENGTH_LONG).show(); // Error handling
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else if (q == retrofitAnalyticsTwo) {
                        Toast.makeText(LoginActivity.this, tempArr[2], Toast.LENGTH_LONG).show(); // Error handling
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, tempArr[2], Toast.LENGTH_LONG).show(); // Error handling
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials! Try again!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private Long retrofitAnalytics(String f) {
        return Long.parseLong(f);
    }
}