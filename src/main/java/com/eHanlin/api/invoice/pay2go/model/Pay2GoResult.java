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
     * 檢查碼
     *
     *  用來檢查此次資料回傳的合法性,串接時可以比對此參數資料,來檢核是否為智付寶平台所回傳
     */
    private String CheckCode;

    public String getMerchantID() {
        return MerchantID;
    }

    public String getInvoiceTransNo() {
        return InvoiceTransNo;
    }

    public String getMerchantOrderNo() {
        return MerchantOrderNo;
    }

    public int getTotalAmt() {
        return TotalAmt;
    }

    public String getRandomNum() {
        return RandomNum;
    }

    public String getCheckCode() {
        return CheckCode;
    }

}

