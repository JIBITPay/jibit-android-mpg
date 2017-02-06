package co.nilin.inapppurchasesample;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.IOException;

/**
 * Created by meikiem on 11/26/16.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance;
    public static Context context;

    private Typeface mLalezar;
    private Typeface mIranSans;
    private Typeface mIranSansLight;
    private Typeface mIranSansMedium;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = getApplicationContext();
        loadTypeface();

    }

    public void loadTypeface() {
        AssetManager mgr = getAssets();
        try {
            mgr.open("Lalezar-Regular.ttf");
            mLalezar = Typeface.createFromAsset(mgr, "Lalezar-Regular.ttf");
            mgr.open("IRANSansMobile(FaNum).ttf");
            mIranSans = Typeface.createFromAsset(mgr, "IRANSansMobile(FaNum).ttf");
            mgr.open("IRANSansMobile(FaNum)_Light.ttf");
            mIranSansLight = Typeface.createFromAsset(mgr, "IRANSansMobile(FaNum)_Light.ttf");
            mgr.open("IRANSansMobile(FaNum)_Medium.ttf");
            mIranSansMedium = Typeface.createFromAsset(mgr, "IRANSansMobile(FaNum)_Medium.ttf");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static synchronized MyApplication getInstance() {
        return sInstance;
    }


    public Typeface getLalezar() {
        return mLalezar;
    }

    public Typeface getIranSans() {
        return mIranSans;
    }

    public Typeface getIranSansLight() {
        return mIranSansLight;
    }

    public Typeface getIranSansMedium() {
        return mIranSansMedium;
    }


}
