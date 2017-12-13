package mx.com.solutions.devare.coppelstore.data.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    public static Retrofit getClient(String baseUrl,RxJava2CallAdapterFactory rxJava2CallAdapterFactory ,GsonConverterFactory converterFactory , OkHttpClient client){

        return   new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .addConverterFactory(converterFactory)
                    .client(client)
                    .build();
    }

}
