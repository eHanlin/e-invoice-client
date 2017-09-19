package com.eHanlin.api.invoice.pay2go;

import java.util.Map;

public abstract class Pay2GoResponse<T> {

    public static final String SUCCESS_STATUS = "SUCCESS";

    ResponseBody responseBody;

    public Pay2GoResponse(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public String getStatus() {
        return responseBody.Status;
    }

    public String getMessage() {
        return responseBody.Message;
    }

    /**
     * 判斷回應是否成功
     */
    public boolean isResultSuccess() {
        return SUCCESS_STATUS.equals(responseBody.Status);
    }

    /**
     * 回應查詢結果
     */
    public String getResultString() {
        return isResultSuccess() ? (String) responseBody.Result : null;
    }

    /**
     * 將 JSON 回應內容解析成 Map
     */
    abstract public Map getResultMap();

    /**
     * 將 JSON 回應內容解析成特定的模型
     */
    abstract public T getResult();

    /**
     * 智付寶 API 回應主體
     */
    static class ResponseBody {

        /**
         * 回應狀態
         */
        String Status;

        /**
         * 回應訊息
         */
        String Message;

        /**
         * 回應結果
         * PS: 該死的 Pay2Go 回應結果型態會依據回應狀態而有不同，成功時是字串，失敗時是個完全沒用的空陣列
         */
        Object Result;

    }

}

