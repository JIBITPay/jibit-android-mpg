package co.nilin.inapppurchasesample.rest;

import co.nilin.inapppurchasesample.rest.model.NewResponse;
import co.nilin.inapppurchasesample.rest.model.QueryResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by meikiem on 11/29/16.
 */

public interface RestApi {

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("api/v1/query")
    Call<QueryResponse> query(@Header("Authorization") String authorization, @Header("Merchant-Code") String merchantCode, @Field("orderId") String orderId, @Field("clientCode") String clientCode);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Cache-Control: no-cache"})
    @FormUrlEncoded
    @POST("api/v1/new")
        Call<NewResponse> newQuery(@Header("Authorization") String authorization, @Header("Merchant-Code") String merchantCode, @Field("acqId") String acqId, @Field("returnUrl") String returnUrl, @Field("notifyUrl") String notifyUrl, @Field("merchId") String merchId, @Field("productInfo") String productInfo, @Field("bizId") String bizId, @Field("sendTime") String sendTime, @Field("amount") String amount, @Field("merchName") String merchName, @Field("txnTp") String txnTp);
}
