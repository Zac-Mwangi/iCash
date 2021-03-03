package com.example.icash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterActivity extends AppCompatActivity {

    EditText fullname,username,email,password,confirm_password;
    RadioButton male,female;
    Button register;
    RadioGroup radioGroup;
    Boolean genderCheck;
    String gender;
    Vibrator v;

    private String register_url = SavedInfo.Url+"register.php";
   //String register_url  = "http://192.168.1.47/iCash/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       fullname = findViewById(R.id.et_fullname);
       username = findViewById(R.id.et_username);
       email = findViewById(R.id.et_email);
       password = findViewById(R.id.et_password);
       confirm_password = findViewById(R.id.et_confirm_password);
       male = findViewById(R.id.radio_male);
       female = findViewById(R.id.radio_female);
       radioGroup = findViewById(R.id.radio_group);
       register = findViewById(R.id.btn_register);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        radioCheck();

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              // Toast.makeText(RegisterActivity.this, "ghgj", Toast.LENGTH_SHORT).show();
            validateInput();
           }
       });


    }

    public void radioCheck() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (male.isChecked()) {
                    genderCheck = true;
                    gender = "Male";
                    //Toast.makeText(RegisterActivity.this, gender, Toast.LENGTH_SHORT).show();
                } else if (female.isChecked()) {
                    genderCheck = true;
                    gender = "Female";
                    // Toast.makeText(RegisterActivity.this, gender, Toast.LENGTH_SHORT).show();
                } else {
                    genderCheck = false;
                    gender = "Empty";
                    v.vibrate(100);
                    register.setEnabled(true);
                    //Toast.makeText(RegisterActivity.this, gender, Toast.LENGTH_SHORT).show();
                }


              /*  if(genderCheck = true){
                    Toast.makeText(RegisterActivity.this, "fine", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Gender Must be checked", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    public void validateInput() {
        //getting values

        final String userNameFinal = username.getText().toString().trim();
        final String fullNameFinal = fullname.getText().toString().trim();
        final String passwordFinal = password.getText().toString().trim();
        final String RePassFinal = confirm_password.getText().toString().trim();
        final String emailFinal = email.getText().toString().trim();

        //check if EditText are Filled
       if(TextUtils.isEmpty(fullNameFinal)){
            fullname.setError("Please enter your Fullname");
            fullname.requestFocus();
            v.vibrate(100);
            register.setEnabled(true);
            return;
        }
        if(TextUtils.isEmpty(userNameFinal)){
            username.setError("Please enter your Username");
            username.requestFocus();
            v.vibrate(100);
            register.setEnabled(true);
            return;
        }

        if(TextUtils.isEmpty(emailFinal)){
            email.setError("Email cannot be Empty");
            email.requestFocus();
            v.vibrate(100);
            register.setEnabled(true);
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailFinal).matches()){
            email.setError("Invalid Email");
            email.requestFocus();
            v.vibrate(100);
            register.setEnabled(true);
            return;

        }

        if(TextUtils.isEmpty(passwordFinal)){
            password.setError("Password Field is empty");
            password.requestFocus();
            v.vibrate(100);
            register.setEnabled(true);
            return;
        }

        if(TextUtils.isEmpty(RePassFinal)){
            confirm_password.setError("Confirm Password Field Empty");
            confirm_password.requestFocus();
            v.vibrate(100);
            register.setEnabled(true);
            return;
        }

        if (!passwordFinal.equals(RePassFinal)) {
            confirm_password.setError("Password and Confirm Password does not match");
            confirm_password.requestFocus();
            v.vibrate(100);
            register.setEnabled(true);
            return;
        }

        //sign Up If everything is fine
        Register();
        //Toast.makeText(this, "fine", Toast.LENGTH_SHORT).show();
    }

    private void Register() {
        StringRequest request = new StringRequest(Request.Method.POST, register_url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                final String ValUserName = username.getText().toString().trim();
                                Intent intent = new Intent(RegisterActivity.this, SecurityQuestionsActivity.class).putExtra("DataFromRegistrationActivity",ValUserName);
                                startActivity(intent);
                                Toast.makeText(RegisterActivity.this, "Registration Successful : Last step", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "err : Fill the Gender part or check internet connection ", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("username", "Ayee");
                //params.put("password", "123");

                params.put("username", username.getText().toString().trim());
                params.put("fullname", fullname.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                params.put("email",email.getText().toString().trim());
               params.put("gender",gender);

                return params;

            }
        };
        Volley.newRequestQueue(this).add(request);


    }


}