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
import android.widget.TextView;
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

public class ForgotPasswordActivity extends AppCompatActivity {
EditText username,answer;
TextView generatedQuetion;
Vibrator v;
Button generateQuiz,submitQuiz;

private String genQuiz_url = SavedInfo.Url+"genQuiz_url.php";
private String submit_Quiz = SavedInfo.Url+"submit_Quiz.php";
private String secQuiz="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        username = findViewById(R.id.usernameFP);
        submitQuiz = findViewById(R.id.submit);
        answer = findViewById(R.id.security_question_answer);
        generateQuiz = findViewById(R.id.generate_securityQuiz);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        generateQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateGenQuestion();
                //Toast.makeText(ForgotPasswordActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        submitQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer();
               // Toast.makeText(ForgotPasswordActivity.this, secQuiz, Toast.LENGTH_SHORT).show();
               // Toast.makeText(ForgotPasswordActivity.this, "ayee", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void validateGenQuestion(){
        final String userNameFinal = username.getText().toString().trim();
        if(TextUtils.isEmpty(userNameFinal)){
            username.setError("Please enter your username");
            username.requestFocus();
            v.vibrate(100);
            generateQuiz.setEnabled(true);
            return;
        }
        genQuestion();

    }
    public void genQuestion(){
        generatedQuetion = findViewById(R.id.genQuizFp);
        StringRequest request = new StringRequest(Request.Method.POST, genQuiz_url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        generatedQuetion.setText(obj.getString("securityQuiz"));
                        secQuiz = obj.getString("securityQuiz");

                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("securityQuiz"), Toast.LENGTH_LONG).show();
                        generatedQuetion.setText(obj.getString("securityQuiz"));
                        secQuiz = obj.getString("securityQuiz");
                        /*final String ValUserName = passData;
                        Intent intent = new Intent(SecurityQuestionsActivity.this, LoginActivity.class).putExtra("DataFromRegistrationActivity",ValUserName);
                        startActivity(intent);

                        Toast.makeText(SecurityQuestionsActivity.this, "Registration complete you can now login", Toast.LENGTH_SHORT).show();
                    */}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "err : check internet connection ", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("username",username.getText().toString().trim());

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }


    public void submitAnswer(){

        StringRequest request = new StringRequest(Request.Method.POST, submit_Quiz,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                    } else {
                       // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                        final String ValUserName = username.getText().toString();
                        Intent intent = new Intent(ForgotPasswordActivity.this, ChangePasswordActivity.class).putExtra("DataFromForgotPasswordActivity",ValUserName);
                        startActivity(intent);

                        Toast.makeText(ForgotPasswordActivity.this, "Data Check complete you can now change password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "err : check internet connection ", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //security_answer_string = security_answer.toString().toLowerCase().trim();

                params.put("username",username.getText().toString().trim());
                params.put("secQuiz",secQuiz);
                params.put("answer",answer.getText().toString().trim());
                /*params.put("username","aye");
                params.put("secQuiz","Favorite food?");
                params.put("answer","mukimo");*/

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}