package com.eHanlin.api.invoice.pay2go.model;

/**
 * 觸發開立發票系統回應訊息
 */
public class InvoiceTouchIssueResult extends Pay2GoResult {

    /**
     * 發票號碼
     */
    private String InvoiceNumber;

    /**
     * 開立發票時間
     *
     *  例:2014-09-25 12:12:12。
     */
    private String CreateTime;

    /**
     * 發票條碼
     */
    private String BarCode;

    /**
     * 發票 QRCode (左)
     */
    private String QRcodeL;

    /**
     * 發票 QRCode (右)
     */
    private String QRcodeR;

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public String getBarCode() {
        return BarCode;
    }

    public String getQRcodeL() {
        return QRcodeL;
    }

    public String getQRcodeR() {
        return QRcodeR;
    }

}

