package com.eHanlin.api.invoice.pay2go.api;

/**
 * 智付寶發票作廢
 */
@SuppressWarnings("unused")
public class InvoiceInvalid extends Pay2GoAPI<InvoiceInvalid> {

    /**
     * API 名稱
     */
    public final static String API_NAME = "invoice_invalid";

    /**
     * API 版本
     */
    public static final String VERSION = "1.0";

    public InvoiceInvalid(String invoiceNumber, String invalidReason) {
        super(API_NAME, VERSION);
        this.setInvoiceNumber(invoiceNumber)
            .setInvalidReason(invalidReason);
    }

    public String name() {
        return API_NAME;
    }

    /**
     * 智付寶開立序號
     */
    public InvoiceInvalid setInvoiceNumber(String invoiceNumber) {
        return setParam("InvoiceNumber", invoiceNumber);
    }

    /**
     *  發票金額
     */
    public InvoiceInvalid setInvalidReason(String invalidReason) {
        return setParam("InvalidReason", invalidReason);
    }

}

