package hse.sheduleteachers.Storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import hse.sheduleteachers.Activity.MainActivity;
import hse.sheduleteachers.Activity.MainScreen;

import static android.content.Context.MODE_PRIVATE;

public class SessionManager {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    /*
    * Сохранение авторизации
    * */
    public void createSession(){
        editor.putBoolean(LOGIN, true);
        editor.apply();
    }

    //Если пользователь авторизован
    public static boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    /*
    * Выход из аккаунта
    * */
    public void logout(){
        editor.clear();
        editor.commit();
        context.startActivity(new Intent(context, MainActivity.class));
    }

    /*
    * Сохранение символьных данных
    * */
    public static void saveData(String name, String value){
        editor.putString(name, value);
        editor.apply();
    }

    /*
    * Сохранение числовых данных
    * */
    public static void saveData(String name, int value){
        editor.putInt(name, value);
        editor.apply();
    }

    /*
    * Получение данных строкой
    * */
    public static String getDataString(String name){
        return sharedPreferences.getString(name,null);
    }

    /*
    * Получение данных числом
    * */
    public static int getDataInt(String name){
        return sharedPreferences.getInt(name,0);
    }

}
