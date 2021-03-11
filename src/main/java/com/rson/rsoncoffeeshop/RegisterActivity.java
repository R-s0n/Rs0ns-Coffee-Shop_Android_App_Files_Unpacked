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

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etRUserName);
        etPassword = findViewById(R.id.etRPassword);

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        findViewById(R.id.tvLoginLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement sessionManagement = new SessionManagement(RegisterActivity.this);
        Long userId = sessionManagement.getSession();
        if (userId != -1){
            Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void registerUser() {
        Toast.makeText(RegisterActivity.this, "Registration has been disabled.  Sorry for the inconvenience!", Toast.LENGTH_LONG).show();
    }


//    private void registerUser() {
//        String userName = etUsername.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//
//        if (userName.isEmpty()) {
//            etUsername.setError("Username is required");
//            etUsername.requestFocus();
//            return;
//        } else if (password.isEmpty()) {
//            etPassword.setError("Password is required");
//            etPassword.requestFocus();
//            return;
//        }
//
//        Call<ResponseBody> call = RetrofitClient
//                .getInstance()
//                .getAPI()
//                .createUser(new User(userName, password));
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String s = "";
//                try {
//                    s = response.body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if (s.equals("SUCCESS")) {
//                    Toast.makeText(RegisterActivity.this, "Successfully registered. Please login", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                } else {
//                    Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
}