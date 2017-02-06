package co.nilin.inapppurchasesample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.nilin.inapppurchasesample.MyApplication;
import co.nilin.inapppurchasesample.R;
import co.nilin.inapppurchasesample.models.Configuration;
import co.nilin.inapppurchasesample.rest.RestClient;
import co.nilin.inapppurchasesample.rest.model.NewResponse;
import co.nilin.inapppurchasesample.utils.TextUtils;
import co.nilin.inapppurchasesample.widgets.CustomToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int RC_JIBIT_RESULT = 2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        initUI();

        findViewById(R.id.btnBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEmpty = false;
                if (android.text.TextUtils.isEmpty(((EditText) findViewById(R.id.etMerchantCode)).getText())) {
                    ((EditText) findViewById(R.id.etMerchantCode)).setError(getString(R.string.insert_merchant_code_is_necessary));
                    isEmpty = true;
                }
                if (android.text.TextUtils.isEmpty(((EditText) findViewById(R.id.etMerchantName)).getText())) {
                    ((EditText) findViewById(R.id.etMerchantName)).setError(getString(R.string.insert_merchant_name_is_necessary));
                    isEmpty = true;
                }
                if (android.text.TextUtils.isEmpty(((EditText) findViewById(R.id.etCallbackUrl)).getText())) {
                    ((EditText) findViewById(R.id.etCallbackUrl)).setError(getString(R.string.insert_callback_is_necessary));
                    isEmpty = true;
                }
                if (!isEmpty) {
                    serviceTransaction();
                }
            }
        });
    }

    private void initUI() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        TextUtils.setText((TextView) findViewById(R.id.toolbar_title), getString(R.string.app_name), MyApplication.getInstance().getLalezar());
        TextUtils.setText((TextView) findViewById(R.id.tvMerchantCode), getString(R.string.merchant_code), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvMerchantName), getString(R.string.merchant_name), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvProductName), getString(R.string.product_name), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvProductDesc), getString(R.string.product_desc), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvCount), getString(R.string.count), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvTotalPrice), getString(R.string.total_price), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvCallbackUrl), getString(R.string.callbacl_url), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvTomans), getString(R.string.tomans), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvBizOrderId), getString(R.string.order_id), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etMerchantCode), Configuration.MERCHANT_ID, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etMerchantName), Configuration.MERCHANT_NAME, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etProductName), Configuration.PRODUCT_NAME, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etProductDesc), Configuration.PRODUCT_DESCRIPTION, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etCount), Configuration.NUMBER_OF_PRODUCT, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etTotalPrice), Configuration.TOTAL_PRICE, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etCallbackUrl), Configuration.CALLBACK_URL, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((EditText) findViewById(R.id.etBizOrderId), Configuration.BIZ_ORDER_ID, MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((Button) findViewById(R.id.btnBuy), getString(R.string.buy), MyApplication.getInstance().getIranSans());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_JIBIT_RESULT && resultCode == RESULT_OK) {
            startActivity(data.setClass(this, ReceiptActivity.class));
        }
    }

    public void serviceTransaction() {
        RestClient.getInstance().newQuery(Configuration.AUTHORIZATION,
                Configuration.MERCHANT_CODE,
                Configuration.ACQ_ID,
                Configuration.RETURN_URL,
                Configuration.NOTIFY_URL,
                ((EditText) findViewById(R.id.etMerchantCode)).getText().toString(),
                ((EditText) findViewById(R.id.etProductName)).getText().toString(),
                ((EditText) findViewById(R.id.etBizOrderId)).getText().toString(),
                new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()),
                ((EditText) findViewById(R.id.etTotalPrice)).getText().toString(),
                ((EditText) findViewById(R.id.etMerchantName)).getText().toString(),
                "20").enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                if (response.isSuccessful() && response.body().getClientActionCode() == 0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("jibit://payment").buildUpon()
                                        .appendQueryParameter("m", ((EditText) findViewById(R.id.etMerchantCode)).getText().toString())
                                        .appendQueryParameter("n", ((EditText) findViewById(R.id.etMerchantName)).getText().toString())
                                        .appendQueryParameter("p", ((EditText) findViewById(R.id.etProductName)).getText().toString())
                                        .appendQueryParameter("d", ((EditText) findViewById(R.id.etProductDesc)).getText().toString())
                                        .appendQueryParameter("c", ((EditText) findViewById(R.id.etCount)).getText().toString())
                                        .appendQueryParameter("a", ((EditText) findViewById(R.id.etTotalPrice)).getText().toString())
                                        .appendQueryParameter("cu", ((EditText) findViewById(R.id.etCallbackUrl)).getText().toString())
                                        .appendQueryParameter("boi", ((EditText) findViewById(R.id.etBizOrderId)).getText().toString())
                                        .build());
                        startActivityForResult(intent, RC_JIBIT_RESULT);

                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                } else {
                    new CustomToast(MainActivity.this, getString(R.string.some_error_happened));
                }
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                new CustomToast(MainActivity.this, getString(R.string.some_error_happened));
            }
        });
    }
}
