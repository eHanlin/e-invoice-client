package com.eHanlin.api.invoice.pay2go.model;

/**
 * Pay2Go 基礎回應結果，包含了所有用來檢驗回應可靠性的欄位
 */
public abstract class Pay2GoResult {

    /**
     * 商店代號
     */
    private String MerchantID;

    /**
     * 智付寶開立序號
     */
    private String InvoiceTransNo;

    /**
     * 發票號碼
     */
    private String InvoiceNumber;

    /**
     * 自訂編號
     */
    private String MerchantOrderNo;

    /**
     * 發票金額
     */
    private int TotalAmt;

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

    public final String getMerchantID() {
        return MerchantID;
    }

    public final String getInvoiceTransNo() {
        return InvoiceTransNo;
    }

    public final String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public final String getMerchantOrderNo() {
        return MerchantOrderNo;
    }

    public final int getTotalAmt() {
        return TotalAmt;
    }

    public final String getRandomNum() {
        return RandomNum;
    }

    public final String getCreateTime() {
        return CreateTime;
    }

    public final String getCheckCode() {
        return CheckCode;
    }

}

