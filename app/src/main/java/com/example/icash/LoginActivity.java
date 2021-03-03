package com.example.icash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login,register;
    TextView forgot_password;
    Vibrator v;
    LinearLayout LL;

    private ProgressDialog pDialog;

    String PassData,PassDtaFromChangePassword;

    String device;

    private final String login_url = SavedInfo.Url+"login.php";
    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.registerBtn);
        LL = findViewById(R.id.mll);
        forgot_password = findViewById(R.id.forgot_passwordTv);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            PassData = intent.getStringExtra("DataFromRegistrationActivity");
            username.setText(PassData);
        }
        Intent intentChangePassword = getIntent();
        if(intentChangePassword.getExtras() != null) {
            PassDtaFromChangePassword = intentChangePassword.getStringExtra("DataFromChangePasswordActivity");
            username.setText(PassDtaFromChangePassword);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });
    }
    public void validateInput() {
        //getting values

        final String userNameFinal = username.getText().toString().trim();
        final String passwordFinal = password.getText().toString().trim();


        //check if EditText are Filled
        if (TextUtils.isEmpty(userNameFinal)) {
            username.setError("Please enter your username or email");
            username.requestFocus();
            v.vibrate(100);
            login.setEnabled(true);
            return;
        }
        if (TextUtils.isEmpty(passwordFinal)) {
            password.setError("Please enter your Password");
            password.requestFocus();
            v.vibrate(100);
            login.setEnabled(true);
            return;
        }
        //Login If everything is fine
        Login();
    }

    private void Login() {
//display loader
        displayLoader();
       // showSimpleProgressDialog(this, "Loading...","Please be patient",false);

        StringRequest request = new StringRequest(Request.Method.POST, login_url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //dismiss loader
                pDialog.dismiss();

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {



                        String Username = obj.getString("username");

                        int loggedInDevices = obj.getInt("devices_logged_in");
                        
                        if(loggedInDevices <= 1)
                        {
                             device = "device";
                        }else{
                             device = "devices";
                        }
                        Toast.makeText(LoginActivity.this, "You are now logged in with "+loggedInDevices+ " "+device, Toast.LENGTH_LONG).show();
                        //storing the user in shared preferences
                        SharedPref.getInstance(getApplicationContext()).storeUserName(Username);
                        //starting the Dashboard activity

                        finish();
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Snackbar snackbar = Snackbar
                        .make(LL, "Check your Internet Connection", Snackbar.LENGTH_INDEFINITE).setActionTextColor(Color.YELLOW).setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                        /*startActivity(getIntent());
                                        finish();
                                        overridePendingTransition(0, 0);*/
                            }
                        });
                snackbar.show();
                //Display error message whenever an error occurs
            }
        }){
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
    private void displayLoader() {
        pDialog = new ProgressDialog(LoginActivity.this,R.style.MyAlertDialogStyle);
        pDialog.setMessage("Logging in please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
}