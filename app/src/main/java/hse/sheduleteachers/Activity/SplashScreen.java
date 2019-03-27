package hse.sheduleteachers.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import hse.sheduleteachers.R;
import hse.sheduleteachers.Storage.SessionManager;

public class SplashScreen extends AppCompatActivity {

    SessionManager sessionManager;
    Context context = this;
    Thread timer = new Thread();
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        sessionManager = new SessionManager(this);

        if(sessionManager.isLoggin()){
            i = new Intent(getApplicationContext(),MainScreen.class);
        } else {
            i = new Intent(getApplicationContext(),MainActivity.class);
        }

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        timer.start();
        Animatoo.animateFade(this);
    }
}
