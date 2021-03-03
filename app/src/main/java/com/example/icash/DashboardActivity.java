package com.example.icash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.icash.MainActivity;
import com.example.icash.R;
import com.example.icash.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {

    TextView user;
    String PassData,device;
    Button btnLogout;

    private String logout_url = SavedInfo.Url+"logout.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        user = findViewById(R.id.tv_fromServer);
        btnLogout = findViewById(R.id.btn_logout);


        if (!SharedPref.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
//getting logged in user name
        String loggedUsername = SharedPref.getInstance(this).LoggedInUser();
        user.setText(loggedUsername);

//logging out
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();
                finish();
                SharedPref.getInstance(getApplicationContext()).logout();
            }
        });
    }
    private void logout() {
        StringRequest request = new StringRequest(Request.Method.POST, logout_url ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        int loggedInDevices = obj.getInt("devices_logged_in");

                        if(loggedInDevices <= 1){
                            device = "device";
                        }
                        else{
                            device = "devices";
                        }

                        Toast.makeText(DashboardActivity.this, "You are now logged in with "+loggedInDevices+ " "+device, Toast.LENGTH_LONG).show();
                            }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "err : during Logout ", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", user.getText().toString().trim());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}





