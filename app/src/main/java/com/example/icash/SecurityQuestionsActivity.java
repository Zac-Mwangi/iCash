package com.example.icash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SecurityQuestionsActivity extends AppCompatActivity {

    private Spinner spinner;

    private JSONArray result;

    private ArrayList<String> arrayList;

    private String selected;

    String passData;

    Button submit;

    EditText security_answer;

    String security_answer_string;

    private final String getQuiz_url = SavedInfo.Url+"getSecurityQuiz.php";
    private final String update_security = SavedInfo.Url+"updateSecurity.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_questions);

        spinner = findViewById(R.id.spinner);

        submit = findViewById(R.id.submit);
        security_answer = findViewById(R.id.security_question_answer);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSecurity();
            }
        });


        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            passData = intent.getStringExtra("DataFromRegistrationActivity");
           // Toast.makeText(this, passData, Toast.LENGTH_SHORT).show();
        }

        //spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        getSecurityQuiz();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selected = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
               /* if (selected.equals("Favorite book?")){
                    selected="Favorite book?";
                }else if(selected.equals("Your hobby?")){
                    selected="Your hobby";
                }else if(selected.equals("First company you worked for?")){
                    selected="First company you worked for?";
                }else if(selected.equals("Your Best Author?")){
                    selected="Your Best Author?";
                }else if(selected.equals("Best Friend?")){
                    selected="Best Friend?";
                }else if(selected.equals("Favorite food?")){
                    selected="Favorite food?";
                }else if(selected.equals("City you were born in?")){
                    selected="City you were born in?";
                }else if(selected.equals("place for vacation?")){
                    selected="place for vacation?";
                }else if(selected.equals("Favorite place?")){
                    selected="Favorite place?";
                }else{
                    Toast.makeText(SecurityQuestionsActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                }*/
                //Toast.makeText(SecurityQuestionsActivity.this, selected, Toast.LENGTH_SHORT).show();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    private void getSecurityQuiz(){
        arrayList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(getQuiz_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j  = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray("result");
                            getQuiz(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SecurityQuestionsActivity.this, "err : Check internet connection", Toast.LENGTH_SHORT).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getQuiz(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                arrayList.add(json.getString("security_questions"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(new ArrayAdapter<String>(SecurityQuestionsActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList));


    }
    private void updateSecurity() {

        StringRequest request = new StringRequest(Request.Method.POST, update_security,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {
                        final String ValUserName = passData;
                        Intent intent = new Intent(SecurityQuestionsActivity.this, LoginActivity.class).putExtra("DataFromRegistrationActivity",ValUserName);
                        startActivity(intent);
                        Toast.makeText(SecurityQuestionsActivity.this, "Registration complete you can now login", Toast.LENGTH_SHORT).show();
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //security_answer_string = security_answer.toString().toLowerCase().trim();
                params.put("username", passData);
                params.put("selected",selected);
                params.put("answer",security_answer.getText().toString().trim().toLowerCase());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

}