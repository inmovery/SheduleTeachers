package hse.sheduleteachers.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import hse.sheduleteachers.R;
import hse.sheduleteachers.Storage.SessionManager;

public class SplashScreen extends AppCompatActivity {

    SessionManager sessionManager;//для работы с SharedPreferences
    Intent i;//на какой экран перейти

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        sessionManager = new SessionManager(this);//записали с чём работать


        /*
        * Проверка на авторизованность преподавателя
        * */
        if(sessionManager.isLoggin()){
            i = new Intent(getApplicationContext(),MainScreen.class);
        } else {
            i = new Intent(getApplicationContext(),MainActivity.class);
        }

        /*
        * Заглушка на 2 минуты и запуск нового активити
        * */
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    startActivity(i);
                    Animatoo.animateFade(SplashScreen.this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.start();
    }
}
