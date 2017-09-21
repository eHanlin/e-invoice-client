package com.eHanlin.api.invoice.pay2go.model;

/**
 * 查詢發票系統回應訊息
 */
public class InvoiceSearchResult extends Pay2GoResult {

    /**
     * 發票號碼
     */
    private String InvoiceNumber;

    /**
     * 買受人名稱
     */
    private String BuyerName;

    /**
     * 買受人統一編號
     */
    private String BuyerUBN;

    /**
     * 買受人地址
     */
    private String BuyerAddress;

    /**
     * 買受人手機號碼
     */
    private String BuyerPhone;

    /**
     * 買受人電子信箱
     */
    private String BuyerEmail;

    /**
     * 發票字軌類型
     */
    private String InvoiceType;

    /**
     * 發票種類
     */
    private String Category;

    /**
     * 課稅別
     */
    private String TaxType;

    /**
     * 稅率
     */
    private String TaxRate;

    /**
     * 銷售額合計
     */
    private String Amt;

    /**
     * 銷售額
     * 1.純數字,為發票銷售額(課稅別應稅的未稅金額)。 2.當 TaxType =9 混合應稅與免稅或零稅率時,才提 供此參數。
     */
    private String AmtSales;

    /**
     * 銷售額
     * 1.純數字,為發票銷售額(課稅別零稅率的未稅金額)。 2.當 TaxType =9 混合應稅與免稅或零稅率時,才提 供此參數。
     */
    private String AmtZero;

    /**
     * 銷售額
     * 1.純數字,為發票銷售額(課稅別免稅的未稅金額)。 2.當 TaxType =9 混合應稅與免稅或零稅率時,才提 供此參數。
     */
    private String AmtFree;

    /**
     * 稅額
     * 純數字,為發票稅額。
     */
    private String TaxAmt;

    /**
     * 載具類別
     *  1.該張發票儲存的載具類別。
     *      0=手機條碼載具
     *      1=自然人憑證條碼載具
     *      2=智付寶載具
     *  2.若買受人無提供載具,則此參數為空值。
     *  3.當 Category=B2C 時,才適用此參數。
     */
    private String CarrierType;

    /**
     * 載具編號
     * 該張發票儲存的載具之載具編號。
     */
    private String CarrierNum;

    /**
     * 愛心碼
     */
    private String LoveCode;

    /**
     * 索取紙本發票
     */
    private String PrintFlag;

    /**
     * 開立發票時間
     */
    private String CreateTime;

    /**
     * 商品明細
     * 回應是 json string ... fuck u
     */
    private String ItemDetail;

    /**
     * 發票狀態
     */
    private String InvoiceStatus;

    /**
     * 發票上傳狀態
     */
    private String UploadStatus;

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public String getBuyerName() {
        return BuyerName;
    }

    public String getBuyerUBN() {
        return BuyerUBN;
    }

    public String getBuyerAddress() {
        return BuyerAddress;
    }

    public String getBuyerPhone() {
        return BuyerPhone;
    }

    public String getBuyerEmail() {
        return BuyerEmail;
    }

    public String getInvoiceType() {
        return InvoiceType;
    }

    public String getCategory() {
        return Category;
    }

    public String getTaxType() {
        return TaxType;
    }

    public String getTaxRate() {
        return TaxRate;
    }

    public String getAmt() {
        return Amt;
    }

    public String getAmtSales() {
        return AmtSales;
    }

    public String getAmtZero() {
        return AmtZero;
    }

    public String getAmtFree() {
        return AmtFree;
    }

    public String getTaxAmt() {
        return TaxAmt;
    }

    public String getCarrierType() {
        return CarrierType;
    }

    public String getCarrierNum() {
        return CarrierNum;
    }

    public String getLoveCode() {
        return LoveCode;
    }

    public String getPrintFlag() {
        return PrintFlag;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public String getItemDetail() {
        return ItemDetail;
    }

    public String getInvoiceStatus() {
        return InvoiceStatus;
    }

    public String getUploadStatus() {
        return UploadStatus;
    }

}

