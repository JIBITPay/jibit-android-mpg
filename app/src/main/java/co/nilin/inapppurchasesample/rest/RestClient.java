package co.nilin.inapppurchasesample.rest;

import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Navid on 8/20/16.
 */
public class RestClient extends AppCompatActivity {
    public static final String API_BASE_URL = "https://checkout.jibit.mobi/";

    private static OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder sRetrofitBuilder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static RestApi getInstance() {
        return sRetrofitBuilder
                .client(sHttpClient.connectTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()).build().create(RestApi.class);
    }

}
