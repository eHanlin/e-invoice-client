package com.eHanlin.api.invoice.pay2go.model;

/**
 * 查詢發票系統回應訊息
 */
public class Pay2GoInvoiceSearchResult {

    /**
     * 商店代號
     */
    public String MerchantID;

    /**
     * 智付寶開立序號
     */
    public String InvoiceTransNo;

    /**
     * 自訂編號
     */
    public String MerchantOrderNo;

    /**
     * 發票號碼
     */
    public String InvoiceNumber;

    /**
     * 發票防偽隨機碼
     */
    public String RandomNum;

    /**
     * 買受人名稱
     */
    public String BuyerName;

    /**
     * 買受人統一編號
     */
    public String BuyerUBN;

    /**
     * 買受人地址
     */
    public String BuyerAddress;

    /**
     * 買受人手機號碼
     */
    public String BuyerPhone;

    /**
     * 買受人電子信箱
     */
    public String BuyerEmail;

    /**
     * 發票字軌類型
     */
    public String InvoiceType;

    /**
     * 發票種類
     */
    public String Category;

    /**
     * 課稅別
     */
    public String TaxType;

    /**
     * 稅率
     */
    public String TaxRate;

    /**
     * 銷售額合計
     */
    public String Amt;

    /**
     * 銷售額
     * 1.純數字,為發票銷售額(課稅別應稅的未稅金額)。 2.當 TaxType =9 混合應稅與免稅或零稅率時,才提 供此參數。
     */
    public String AmtSales;

    /**
     * 銷售額
     * 1.純數字,為發票銷售額(課稅別零稅率的未稅金額)。 2.當 TaxType =9 混合應稅與免稅或零稅率時,才提 供此參數。
     */
    public String AmtZero;

    /**
     * 銷售額
     * 1.純數字,為發票銷售額(課稅別免稅的未稅金額)。 2.當 TaxType =9 混合應稅與免稅或零稅率時,才提 供此參數。
     */
    public String AmtFree;

    /**
     * 稅額
     * 純數字,為發票稅額。
     */
    public String TaxAmt;

    /**
     * 發票金額
     * 純數字,為發票總金額(含稅)。
     */
    public String TotalAmt;

    /**
     * 載具類別
     *  1.該張發票儲存的載具類別。
     *      0=手機條碼載具
     *      1=自然人憑證條碼載具
     *      2=智付寶載具
     *  2.若買受人無提供載具,則此參數為空值。
     *  3.當 Category=B2C 時,才適用此參數。
     */
    public String CarrierType;

    /**
     * 載具編號
     * 該張發票儲存的載具之載具編號。
     */
    public String CarrierNum;

    /**
     * 愛心碼
     */
    public String LoveCode;

    /**
     * 索取紙本發票
     */
    public String PrintFlag;

    /**
     * 開立發票時間
     */
    public String CreateTime;

    /**
     * 商品明細
     * 回應是 json string ... fuck u
     */
    public String ItemDetail;

    /**
     * 發票狀態
     */
    public String InvoiceStatus;

    /**
     * 發票上傳狀態
     */
    public String UploadStatus;

    /**
     * 檢查碼
     */
    public String CheckCode;

}

