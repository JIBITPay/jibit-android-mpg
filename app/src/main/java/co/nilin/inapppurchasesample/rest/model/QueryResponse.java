package co.nilin.inapppurchasesample.rest.model;

/**
 * Created by meikiem on 11/29/16.
 */

public class QueryResponse {
    private int clientActionCode;
    private String error;
    private TransactionInfo transactionInfo;

    public int getClientActionCode() {
        return clientActionCode;
    }

    public void setClientActionCode(int clientActionCode) {
        this.clientActionCode = clientActionCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }
}
