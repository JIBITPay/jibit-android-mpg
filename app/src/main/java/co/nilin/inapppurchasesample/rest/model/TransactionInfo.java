package co.nilin.inapppurchasesample.rest.model;

/**
 * Created by meikiem on 11/29/16.
 */

public class TransactionInfo {

    private String merchantId;
    private long amount;
    private String status;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
