package com.eHanlin.api.invoice.pay2go;

import com.eHanlin.api.invoice.pay2go.api.InvoiceSearch;
import com.eHanlin.api.invoice.pay2go.api.Pay2GoAPI;
import com.eHanlin.api.invoice.pay2go.model.InvoiceSearchResult;
import com.eHanlin.api.invoice.util.CryptoUtil;
import com.eHanlin.api.invoice.util.HttpInvoker;
import com.eHanlin.api.invoice.util.MoshiJsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 智付寶電子發票API
 */
public class Pay2GoInvoice {

    private final String endpoint;

    private final String merchantId;

    private final CryptoUtil cryptoUtil;

    private final HttpInvoker http;

    private final MoshiJsonParser jsonParser;

    public Pay2GoInvoice(String endpoint, String merchantId, String secret, String iv) {
        this.endpoint = endpoint;
        this.merchantId = merchantId;
        this.cryptoUtil = new CryptoUtil(secret, iv);
        this.http = new HttpInvoker();
        this.jsonParser = new MoshiJsonParser();
    }

    /**
     * 使用發票號碼以及發票隨機碼查詢發票
     */
    public Pay2GoResponse search(String invoiceNumber, String randomNum) {
        String responseBody = call(new InvoiceSearch()
            .setSearchType("0")
            .setInvoiceNumber(invoiceNumber)
            .setRandomNum(randomNum)
            .setTimeStamp((System.currentTimeMillis() / 1000) + "")
        );

        return buildPay2GoResponse(InvoiceSearchResult.class, responseBody);
    }

    private String call(Pay2GoAPI api) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("MerchantID_", merchantId);
        requestBody.put("PostData_", cryptoUtil.encrypt(api.param()));
        return http.post(endpoint + api.name(), requestBody);
    }

    private <T> Pay2GoResponse<T> buildPay2GoResponse(Class<T> clazz, String result) {

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

        };

    }

}

