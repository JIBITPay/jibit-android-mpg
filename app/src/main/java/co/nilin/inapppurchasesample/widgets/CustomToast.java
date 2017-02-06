package co.nilin.inapppurchasesample.widgets;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.nilin.inapppurchasesample.MyApplication;
import co.nilin.inapppurchasesample.R;

/**
 * Created by meikiem on 11/29/16.
 */

public class CustomToast extends Toast {

    public CustomToast(Context context, String customText) {
        super(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(customText);
        text.setTypeface(MyApplication.getInstance().getIranSans());

        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}