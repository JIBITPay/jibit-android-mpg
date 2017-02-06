package co.nilin.inapppurchasesample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import co.nilin.inapppurchasesample.MyApplication;
import co.nilin.inapppurchasesample.R;
import co.nilin.inapppurchasesample.models.Configuration;
import co.nilin.inapppurchasesample.rest.RestClient;
import co.nilin.inapppurchasesample.rest.model.QueryResponse;
import co.nilin.inapppurchasesample.utils.TextUtils;
import co.nilin.inapppurchasesample.widgets.CustomToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiptActivity extends AppCompatActivity {


    private String state;
    private String trackingCode;
    private String amount;
    private String clientCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        fetchIntent();
        initUI();
        dataValidation();
    }

    private void fetchIntent() {
        Intent intent = getIntent();
        Uri uri = intent.getData();
        state = uri.getQueryParameter("s");
        trackingCode = uri.getQueryParameter("t");
        amount = uri.getQueryParameter("a");
        clientCode = uri.getQueryParameter("cc");
    }

    private void initUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationIcon(R.drawable.ic_close);
        TextUtils.setText((TextView) findViewById(R.id.toolbar_title), getString(R.string.receipt), MyApplication.getInstance().getLalezar());
        TextUtils.setText((TextView) findViewById(R.id.tvBillType), getString(R.string.receipt), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvState), (state != null ? state : ""), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvTrackingCodeTitle), getString(R.string.tracking_code) + " " + ":", MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvTrackingCode), (trackingCode != null && (!android.text.TextUtils.isEmpty(trackingCode)) ? trackingCode : (android.text.TextUtils.isEmpty(trackingCode) ? "-" : "-")), MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvAmountTitle), getString(R.string.amount) + " " + ":", MyApplication.getInstance().getIranSansMedium());
        TextUtils.setText((TextView) findViewById(R.id.tvAmount), (amount != null ? amount : "") + " " + getString(R.string.tomans), MyApplication.getInstance().getIranSansMedium());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dataValidation() {
        // Authentication : "f6a1ed0d-597f-4ddc-bf1d-112b50c6ca22"
        // Merchant-code : "9835d86bcbf4e74d"
        // orderId : 2932
        RestClient.getInstance().query(Configuration.AUTHORIZATION,
                Configuration.MERCHANT_CODE,
                trackingCode,
                clientCode).enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getTransactionInfo().getAmount() / 10 == Long.parseLong(amount)) {
                        if (response.body().getTransactionInfo().getStatus().equals("SUCCESS"))
                            TextUtils.setText((TextView) findViewById(R.id.tvState), getString(R.string.successful_transaction), MyApplication.getInstance().getIranSansMedium());
                        else
                            TextUtils.setText((TextView) findViewById(R.id.tvState), getString(R.string.unsuccessful_transaction), MyApplication.getInstance().getIranSansMedium());
                    } else
                        TextUtils.setText((TextView) findViewById(R.id.tvState), getString(R.string.invalid_transaction), MyApplication.getInstance().getIranSansMedium());

                }
            }

            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                new CustomToast(ReceiptActivity.this, getString(R.string.some_error_happened));
            }
        });
    }
}
