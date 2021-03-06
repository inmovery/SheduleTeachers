package hse.sheduleteachers.Notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hse.sheduleteachers.Activity.MainScreen;
import hse.sheduleteachers.R;

import static android.content.ContentValues.TAG;

public class FirebaseReceiwer extends FirebaseMessagingService {

    public final int NOTIFICATION_ID = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Проверка пустоты текста сообщения в уведомлениях
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Уведомление: " + remoteMessage.getNotification().getBody());
        }

        showNotification(remoteMessage.getNotification().getBody());

    }


    /*
    * Изменение токена мобильного устройства
    * */
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Новый токен: " + token);
    }

    /*
    * Отправка уведомлений
    * */
    private void showNotification(String message){

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent notify_intent = new Intent(this, MainScreen.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notify_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        @SuppressLint("WrongConstant") Notification.Builder notify_builder = new Notification.Builder(getApplicationContext())
                .setContentTitle("Ваше расписание обновилось!")
                .setContentText(message)
                .setSound(uri)
                .setSmallIcon(R.mipmap.app_icon_round)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent);

        manager.notify(NOTIFICATION_ID, notify_builder.build());

    }

}


