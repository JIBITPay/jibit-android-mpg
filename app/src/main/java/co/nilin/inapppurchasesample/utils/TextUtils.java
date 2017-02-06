package co.nilin.inapppurchasesample.utils;

import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * Created by Navid on 7/3/16.
 */
public class TextUtils {
    private static char[] PERSIAN_CHAR = {'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};

    public static void setText(TextView view, String text, Typeface typeface) {
        view.setTypeface(typeface);
        view.setText(text);
    }

    public static void setText(EditText view, String hint, String text, Typeface typeface) {
        view.setTypeface(typeface);
        view.setHint(hint);
        view.setText(text);
    }

    public static void setText(TextInputLayout viewLayout, EditText view, String hint, String text, Typeface typeface) {
        view.setTypeface(typeface);
        view.setHint(hint);
        view.setText(text);
        viewLayout.setHint(hint);
    }

    public static String getPersianString(int number) {
        String str = String.valueOf(number);
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++)
            sb.append(PERSIAN_CHAR[(int) str.charAt(i) - 48]);
        return sb.toString();
    }

    public static String getPersianString(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            sb.append(Character.isDigit(c) ? PERSIAN_CHAR[(int) c - 48] : c);
        }
        return sb.toString();
    }

    public static String pad(long num) {
        return (num < 10 ? "0" + num : String.valueOf(num));
    }

    public static String getRelativeTime(long time) {
        if (time < 60) return "الآن";
        if (time < 3600)
            return (time / 60) + " " + "دقیقه پیش"/*" minute" + (time / 60 == 1 ? "" : "s") + " ago"*/;
        if (time < 86400)
            return (time / 3600) + " " + "ساعت پیش"/*" hour" + (time / 3600 == 1 ? "" : "s") + " ago"*/;
        if (time < 2592000)
            return (time / 86400) + " " + "روز پیش"/*" day" + (time / 86400 == 1 ? "" : "s") + " ago"*/;
        return (time / 604800) + " " + "هفته پیش";
        /*if (time < 31104000) return (time / 2592000) + " month" + (time / 2592000 == 1 ? "" : "s") + " ago";
        return (time / 31104000) + " year" + (time / 31104000 == 1 ? "" : "s") + " ago";*/
    }

    public static String encryptMd5(String inStr) {
        try {
            byte[] bytesOfMessage = inStr.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);

            return new BigInteger(1, thedigest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) return capitalize(model);
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) return "";

        char first = s.charAt(0);
        if (Character.isUpperCase(first)) return s;
        return Character.toUpperCase(first) + s.substring(1);
    }
}
