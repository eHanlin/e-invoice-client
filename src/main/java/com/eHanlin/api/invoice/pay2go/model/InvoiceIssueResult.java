package com.eHanlin.api.invoice.pay2go.model;

/**
 * 開立發票系統回應訊息
 */
public class InvoiceIssueResult extends Pay2GoResult {

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

