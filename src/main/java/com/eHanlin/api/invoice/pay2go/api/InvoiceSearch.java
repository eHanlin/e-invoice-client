package com.eHanlin.api.invoice.pay2go.api;

/**
 * 智付寶發票查詢
 */
public class InvoiceSearch extends Pay2GoAPI<InvoiceSearch> {

    /**
     * API 名稱
     */
    private final static String API_NAME = "invoice_search";

    /**
     * API 版本
     */
    public static final String VERSION = "1.1";

    public InvoiceSearch() {
        super(API_NAME, VERSION);
    }

    public String name() {
        return API_NAME;
    }

    public InvoiceSearch setSearchType(String searchType) {
        params.put("SearchType", searchType);
        return this;
    }

    public InvoiceSearch setTotalAmt(String totalAmt) {
        params.put("TotalAmt", totalAmt);
        return this;
    }

    public InvoiceSearch setInvoiceNumber(String invoiceNumber) {
        params.put("InvoiceNumber", invoiceNumber);
        return this;
    }

    public InvoiceSearch setRandomNum(String randomNum) {
        params.put("RandomNum", randomNum);
        return this;
    }

}

