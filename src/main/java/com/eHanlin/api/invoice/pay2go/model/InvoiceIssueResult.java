package com.eHanlin.api.invoice.pay2go.model;

/**
 * 開立發票系統回應訊息
 */
public class InvoiceIssueResult {

    /**
     * 商店代號
     */
    private String MerchantID;

    /**
     * 智付寶開立序號
     */
    private String InvoiceTransNo;

    /**
     * 自訂編號
     */
    private String MerchantOrderNo;

    /**
     * 發票金額
     */
    private String TotalAmt;

    /**
     * 發票號碼
     */
    private String InvoiceNumber;

    /**
     * 發票防偽隨機碼
     */
    private String RandomNum;

    /**
     * 開立發票時間
     *
     *  例:2014-09-25 12:12:12。
     */
    private String CreateTime;

    /**
     * 檢查碼
     *
     *  用來檢查此次資料回傳的合法性,串接時可以比對此參數資料,來檢核是否為智付寶平台所回傳
     */
    private String CheckCode;

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

    public String getMerchantID() {
        return MerchantID;
    }

    public String getInvoiceTransNo() {
        return InvoiceTransNo;
    }

    public String getMerchantOrderNo() {
        return MerchantOrderNo;
    }

    public String getTotalAmt() {
        return TotalAmt;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public String getRandomNum() {
        return RandomNum;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public String getCheckCode() {
        return CheckCode;
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

