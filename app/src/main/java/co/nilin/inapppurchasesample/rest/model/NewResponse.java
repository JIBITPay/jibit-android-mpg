package co.nilin.inapppurchasesample.rest.model;

/**
 * Created by meikiem on 12/12/16.
 */

public class NewResponse {
    private int clientActionCode;
    private String message;

    public NewResponse(int clientActionCode, String message) {
        this.clientActionCode = clientActionCode;
        this.message = message;
    }

    public int getClientActionCode() {
        return clientActionCode;
    }

    public void setClientActionCode(int clientActionCode) {
        this.clientActionCode = clientActionCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
