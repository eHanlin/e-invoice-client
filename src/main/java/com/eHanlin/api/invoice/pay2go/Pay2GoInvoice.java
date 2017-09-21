package com.eHanlin.api.invoice.pay2go;

import com.eHanlin.api.invoice.pay2go.api.InvoiceIssue;
import com.eHanlin.api.invoice.pay2go.api.InvoiceSearch;
import com.eHanlin.api.invoice.pay2go.api.InvoiceTouchIssue;
import com.eHanlin.api.invoice.pay2go.api.Pay2GoAPI;
import com.eHanlin.api.invoice.pay2go.model.InvoiceIssueResult;
import com.eHanlin.api.invoice.pay2go.model.InvoiceSearchResult;
import com.eHanlin.api.invoice.pay2go.model.InvoiceTouchIssueResult;
import com.eHanlin.api.invoice.pay2go.model.Pay2GoResult;
import com.eHanlin.api.invoice.util.CryptoUtil;
import com.eHanlin.api.invoice.util.HttpInvoker;
import com.eHanlin.api.invoice.util.MoshiJsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 智付寶電子發票API
 */
@SuppressWarnings("unused")
public class Pay2GoInvoice {

    private final String endpoint;

    private final String merchantId;

    private final CryptoUtil cryptoUtil;

    private final HttpInvoker http;

    private final MoshiJsonParser jsonParser;

    private final String hashKey;

    private final String hashIV;

    public Pay2GoInvoice(String endpoint, String merchantId, String hashKey, String hashIV) {
        this.endpoint = endpoint;
        this.merchantId = merchantId;
        this.cryptoUtil = new CryptoUtil(hashKey, hashIV);
        this.http = new HttpInvoker();
        this.jsonParser = new MoshiJsonParser();
        this.hashKey = hashKey;
        this.hashIV = hashIV;
    }

    /**
     * 使用發票號碼以及發票隨機碼查詢發票
     */
    public Pay2GoResponse<InvoiceSearchResult> search(String invoiceNumber, String randomNum) {
        Pay2GoAPI api = new InvoiceSearch()
                        .setSearchType("0")
                        .setInvoiceNumber(invoiceNumber)
                        .setRandomNum(randomNum)
                        .setTimeStamp((System.currentTimeMillis() / 1000) + "");

        return call(api, InvoiceSearchResult.class);
    }

    /**
     * 開立發票
     */
    public Pay2GoResponse<InvoiceIssueResult> issue(InvoiceIssue api) {
        return call(api, InvoiceIssueResult.class);
    }

    /**
     * 觸發開立發票
     */
    public Pay2GoResponse<InvoiceTouchIssueResult> touchIssue(String merchantOrderNo, String invoiceTransNum, int totalAmt) {
        return touchIssue(new InvoiceTouchIssue(merchantOrderNo, invoiceTransNum, totalAmt));
    }

    /**
     * 觸發開立發票
     */
    public Pay2GoResponse<InvoiceTouchIssueResult> touchIssue(InvoiceTouchIssue api) {
        return call(api, InvoiceTouchIssueResult.class);
    }

    private <T extends Pay2GoResult> Pay2GoResponse<T> call(Pay2GoAPI api, Class<T> clazz) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("MerchantID_", merchantId);
        requestBody.put("PostData_", cryptoUtil.encrypt(api.param()));
        String responseBody = http.post(endpoint + api.name(), requestBody);
        return buildPay2GoResponse(clazz, responseBody);
    }

    private <T extends Pay2GoResult> Pay2GoResponse<T> buildPay2GoResponse(Class<T> clazz, String result) {

        Pay2GoResponse.ResponseBody responseBody = jsonParser.stringTo(Pay2GoResponse.ResponseBody.class, result);

        return new Pay2GoResponse<T>(responseBody) {

            @Override
            public T getResult() {
                return isResultSuccess() ? jsonParser.stringTo(clazz, getResultString()) : null;
            }

            @Override
            public Map getResultMap() {
                return isResultSuccess() ? jsonParser.asMap(getResultString()) : null;
            }

            @Override
            public boolean check() {
                if (!isResultSuccess()) {
                    return false;
                }

                Pay2GoResult result = getResult();
                String s = "HashIV=" + hashIV
                        + "&InvoiceTransNo=" + result.getInvoiceTransNo()
                        + "&MerchantID=" + result.getMerchantID()
                        + "&MerchantOrderNo=" + result.getMerchantOrderNo()
                        + "&RandomNum=" + result.getRandomNum()
                        + "&TotalAmt=" + result.getTotalAmt()
                        + "&HashKey=" + hashKey;

                String recheckData = cryptoUtil.sha256(s).toUpperCase();
                return recheckData.equals(result.getCheckCode());
            }

        };

    }

}

