package hse.sheduleteachers.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;
import java.util.Map;

import hse.sheduleteachers.R;
import hse.sheduleteachers.Storage.SessionManager;

import static hse.sheduleteachers.Storage.SessionManager.getDataInt;
import static hse.sheduleteachers.Storage.SessionManager.getDataString;

public class SettingsScreen extends AppCompatActivity {

    private Button logout_btn;

    SessionManager sessionManager;

    private TextView fio;

    private Button mButton;

    LinearLayout linal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Настройки");

        sessionManager = new SessionManager(this);

        linal = (LinearLayout)findViewById(R.id.linal);

        logout_btn = findViewById(R.id.logout_btn);

        fio = (TextView)findViewById(R.id.settings_text);

        fio.setText("ФИО: "+getDataString("NAME"));

        //Кнопка выйти из аккаунта
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        FirebaseApp.initializeApp(this);
        mButton = (Button)findViewById(R.id.open_push);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( SettingsScreen.this,  new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String token = instanceIdResult.getToken();
                        registerToken(getApplicationContext(),token);
                        Log.e("newToken",token);
                    }
                });
                mButton.setVisibility(View.INVISIBLE);
            }
        });


    }

    void registerToken(Context context, final String token){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://devredowl.ru/tparser/push/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //...
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //...
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("Token",token);
                params.put("Id",String.valueOf(getDataInt("TEACHER_ID")));
                return params;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);

        Snackbar snackbar = Snackbar.make(linal, "Уведомления включены!", Snackbar.LENGTH_INDEFINITE)
                .setAction("ОК", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        // Message text color
        snackbar.setActionTextColor(Color.rgb(124, 12, 176));



        // Action button text color
        View snackBarView = snackbar.getView();

        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                Animatoo.animateFade(this);
                return true;
        }
        //startActivity(new Intent(getApplicationContext(), MainScreen.class));

        return super.onOptionsItemSelected(item);
    }


}
