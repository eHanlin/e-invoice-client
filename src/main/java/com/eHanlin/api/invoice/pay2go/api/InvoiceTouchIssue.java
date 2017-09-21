package com.eHanlin.api.invoice.pay2go.api;

/**
 * 智付寶發票觸發開立
 */
@SuppressWarnings("unused")
public class InvoiceTouchIssue extends Pay2GoAPI<InvoiceTouchIssue> {

    /**
     * API 名稱
     */
    public final static String API_NAME = "invoice_touch_issue";

    /**
     * API 版本
     */
    public static final String VERSION = "1.0";

    public InvoiceTouchIssue(String merchantOrderNo, String invoiceTransNum, int totalAmt) {
        super(API_NAME, VERSION);
        this.setMerchantOrderNo(merchantOrderNo)
            .setInvoiceTransNum(invoiceTransNum)
            .setTotalAmt(totalAmt);
    }

    public String name() {
        return API_NAME;
    }

    /**
     * 智付寶平台交易序號
     */
    public InvoiceTouchIssue setTransNum(String transNum) {
        return setParam("TransNum", transNum);
    }

    /**
     * 智付寶開立序號
     */
    public InvoiceTouchIssue setInvoiceTransNum(String invoiceTransNum) {
        return setParam("InvoiceTransNo", invoiceTransNum);
    }

    /**
     *  發票金額
     */
    public InvoiceTouchIssue setTotalAmt(int totalAmt) {
        return setParam("TotalAmt", totalAmt + "");
    }

}

