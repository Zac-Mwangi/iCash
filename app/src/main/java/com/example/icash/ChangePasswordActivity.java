package com.example.icash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//FOR PASSWORD RESET
public class ChangePasswordActivity extends AppCompatActivity {
    EditText username, password, confirm_password;
    Button reset;
    Vibrator v;
    String PassData;

    private String reset_password = SavedInfo.Url+"reset_password.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        username = findViewById(R.id.usernameCP);
        password = findViewById(R.id.passwordCP);
        confirm_password = findViewById(R.id.confirm_passwordCP);
        reset = findViewById(R.id.resetCP);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            PassData = intent.getStringExtra("DataFromForgotPasswordActivity");
            username.setText(PassData);
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void validate() {
        final String userNameFinal = username.getText().toString().trim();
        final String passwordFinal = password.getText().toString().trim();
        final String RePassFinal = confirm_password.getText().toString().trim();

        if (TextUtils.isEmpty(userNameFinal)) {
            username.setError("Please enter your Username");
            username.requestFocus();
            v.vibrate(100);
            reset.setEnabled(true);
            return;
        }
        if (TextUtils.isEmpty(passwordFinal)) {
            password.setError("Password Field is empty");
            password.requestFocus();
            v.vibrate(100);
            reset.setEnabled(true);
            return;
        }

        if (TextUtils.isEmpty(RePassFinal)) {
            confirm_password.setError("Confirm Password Field Empty");
            confirm_password.requestFocus();
            v.vibrate(100);
            reset.setEnabled(true);
            return;
        }

        if (!passwordFinal.equals(RePassFinal)) {
            confirm_password.setError("Password and Confirm Password does not match");
            confirm_password.requestFocus();
            v.vibrate(100);
            reset.setEnabled(true);
            return;
        }
        reset();
    }

    public void reset() {
        StringRequest request = new StringRequest(Request.Method.POST, reset_password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        //success
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        final String ValUserName = username.getText().toString();
                        Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class).putExtra("DataFromChangePasswordActivity",ValUserName);
                        startActivity(intent);
                        //Toast.makeText(ChangePasswordActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "err : check internet connection ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("username", username.getText().toString().trim());
                params.put("password", password.getText().toString().trim());

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}