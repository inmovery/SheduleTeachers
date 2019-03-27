package hse.sheduleteachers.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    /*Основной URL*/
    private static final String ROOT_URL = "https://devredowl.ru";

    /**
     * Получение экземпляра Retrofit
     * */
    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Инициализация API сервиса
     *
     * @return API Service
     * */
    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }

}



