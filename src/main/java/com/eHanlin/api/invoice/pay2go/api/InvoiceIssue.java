package com.eHanlin.api.invoice.pay2go.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.eHanlin.api.invoice.pay2go.api.InvoiceIssue.Category.*;
import static com.eHanlin.api.invoice.pay2go.api.InvoiceIssue.PrintFlag.*;
import static com.eHanlin.api.invoice.pay2go.api.InvoiceIssue.TaxType.*;
import static com.eHanlin.api.invoice.pay2go.api.InvoiceIssue.Status.*;

/**
 * 智付寶發票開立
 */
@SuppressWarnings("unused")
public class InvoiceIssue extends Pay2GoAPI<InvoiceIssue> {

    /**
     * API 名稱
     */
    public final static String API_NAME = "invoice_issue";

    /**
     * API 版本
     */
    public static final String VERSION = "1.4";

    /**
     * 一般稅率
     */
    static final int NORMAL_TAX_RATE = 5;

    /**
     * 預約開立發票，預計開立日期格式
     */
    static final String CREATE_STATUS_TIME_FORMAT = "yyyy-MM-dd";

    /**
     * 商品明細清單
     */
    private ItemList itemList;

    /**
     * 預設發票設定
     *  1.應稅5%
     *  2.列印紙本發票
     */
    public InvoiceIssue() {
        super(API_NAME, VERSION);
        itemList = new ItemList();
        duty().withPaper();
    }

    public InvoiceIssue(String merchantOrderNo) {
        this();
        setMerchantOrderNo(merchantOrderNo);
    }

    /**
     * 智付寶平台交易序號
     */
    public InvoiceIssue setTransNum(String transNum) {
        return setParam("TransNum", transNum);
    }

    /**
     * 開立發票方式
     *
     *  1=即時開立發票
     *  0=等待觸發開立發票(須於確認要開立時, 再發動觸發)
     *  3=預約自動開立發票(須指定預計開立日期)
     */
    public InvoiceIssue setStatus(Status status) {
        return setParam("Status", status.id());
    }

    /**
     * 預計開立日期
     *
     *  1.當開立發票方式為預約自動開立發票時 (Status=3),才需要帶此參數。
     *  2.格式為 YYYY-MM-DD。 例:2014-10-05
     */
    public InvoiceIssue setCreateStatusTime(String createStatusTime) {
        return setParam("CreateStatusTime", createStatusTime);
    }

    /**
     * 發票種類
     *
     *  B2B=買受人為營業人(有統編)。
     *  B2C=買受人為個人。
     */
    public InvoiceIssue setCategory(Category category) {
        return setParam("Category", category.name());
    }

    /**
     * 買受人名稱
     */
    public InvoiceIssue setBuyerName(String buyerName) {
        return setParam("BuyerName", buyerName);
    }

    /**
     * 買受人統一編號
     *
     *  1.買受人統一編號,純數字。
     *  2.買受人為個人時,則不須填寫。
     */
    public InvoiceIssue setBuyerUBN(String buyerUBN) {
        return setParam("BuyerUBN", buyerUBN);
    }

    /**
     * 買受人地址
     */
    public InvoiceIssue setBuyerAddress(String buyerAddress) {
        return setParam("BuyerAddress", buyerAddress);
    }

    /**
     * 買受人電子信箱
     *
     *  1.買受人的電子信箱。當發票開立時,寄送 發票相關查詢資訊。
     *  2.當 CarrierType=2 時,為智付寶載具,則 此參數為必填。
     */
    public InvoiceIssue setBuyerEmail(String buyerEmail) {
        return setParam("BuyerEmail", buyerEmail);
    }

    /**
     * 載具類別
     *
     *  1.當 Category=B2C 時,才適用此參數。
     *  2.若買受人選擇將發票儲存至載具,則填入 載具類別。
     *      0=手機條碼載具
     *      1=自然人憑證條碼載具
     *      2=智付寶載具
     *  3.若買受人無提供載具,則此參數為空值。
     *  4.當此參數有提供數值時,LoveCode 參數 必為空值。
     *  5.若是其它載具,則直接輸入載具代碼。
     */
    public InvoiceIssue setCarrierType(String carrierType) {
        return setParam("CarrierType", carrierType);
    }

    /**
     * 載具編號
     *
     *  1.若 CarrierType 參數有提供數值時,則此參數為必填。
     *  2.當 CarrierType 為手機條碼載具、自然人憑證條碼載具時,此參數請提供買受人之載具號碼。
     *  3.當 CarrierType 為智付寶載具時，此參數請提供可識別買受人之代號 (例:e-mail、手機號碼、會員編號...等),
     *    由賣方自訂即可, 同一個代號則視為同一個買受人。智付寶將以賣方統編加上買受人代號做為該買受人的智付寶載具號碼。
     *  4.請將該參數值做 encode 處理,確認資料內容不會被濾掉。
     */
    public InvoiceIssue setCarrierNum(String carrierNum) {
        return setParam("CarrierNum", carrierNum);
    }

    /**
     * 愛心碼
     *
     *  1.純數字。
     *  2.當 Category=B2C 時，才適用此參數。
     *  3.買受人選擇捐贈,則於此參數填入受贈單位之愛心碼。買受人選擇不捐贈則此參數為空值。
     *  4.若此參數有提供數值時,則 CarrierType 參數必為空值。
     */
    public InvoiceIssue setLoveCode(String loveCode) {
        return setParam("LoveCode", loveCode);
    }

    /**
     * 索取紙本發票
     *
     *  Y=索取(營業人可於智付寶平台列印此發票)
     *  N=不索取
     *
     *  1.當 Category=B2B 時,則此參數必填 Y。
     *  2.當 Category=B2C 時,若 CarrierType、 LoveCode 參數皆為空值,則此參數必填 Y。
     */
    public InvoiceIssue setPrintFlag(PrintFlag printFlag) {
        return setParam("PrintFlag", printFlag.name());
    }

    /**
     * 課稅別
     *
     *  1=應稅
     *  2=零稅率
     *  3=免稅
     *  9=混合應稅與免稅或零稅率(限收銀機發票無法分辨時使用)
     */
    public InvoiceIssue setTaxType(TaxType taxType) {
        return setParam("TaxType", taxType == null ? null : taxType.id());
    }

    /**
     * 稅率
     *
     *  1.課稅別為應稅時,一般稅率請帶 5,特種稅率請帶入規定的課稅稅率(不含%,例稅率 18%則帶入 18)
     *  2.課稅別為零稅率、免稅時,稅率請帶入 0
     */
    public InvoiceIssue setTaxRate(int taxRate) {
        itemList.setTaxRate(taxRate);
        return setParam("TaxRate", "" + taxRate)
                .calcAmt();
    }

    /**
     * 報關標記
     *
     *  1. 課稅別為零稅率時,才須使用的欄位
     *  2. 課稅別為零稅率時,須帶海關報關出口類別,代號如下:
     *      1 = 非經海關出口
     *      2 = 經海關出口
     */
    public InvoiceIssue setCustomsClearance(String customsClearance) {
        return setParam("CustomsClearance", customsClearance);
    }

    /**
     * 銷售額合計
     *  1.純數字,為發票銷售額(未稅)。
     *  2.銷售額計算方式,請務必與公司財會人員進行確認。
     *  3.當 TaxType = 9 混合應稅與免稅或零稅率時,此欄位為「AmtSales+ AmtZero+ AmtFree 欄位之合計金額。
     */
    public InvoiceIssue setAmt(int amt) {
        return setParam("Amt", "" + amt);
    }

    /**
     * 銷售額 (課稅別應稅)
     *
     *  1.當 TaxType =9 混合應稅與免稅或零稅率時,才須提供此欄位。
     *  2.純數字,為該發票中課稅別應稅之銷售額 (未稅)。
     *  3.銷售額計算方式請務必與公司財會人員進行確認。
     */
    public InvoiceIssue setAmtSales(int amtSales) {
        return setParam("AmtSales", "" + amtSales);
    }

    /**
     * 銷售額 (課稅別零稅率)
     *
     *  1.當 TaxType =9 混合應稅與免稅或零稅率時,才須提供此欄位。
     *  2.純數字,為該發票中課稅別零稅率之銷售額。
     *  3.銷售額計算方式請務必與公司財會人員進行確認。
     */
    public InvoiceIssue setAmtZero(int amtZero) {
        return setParam("AmtZero", "" + amtZero);
    }

    /**
     * 銷售額 (課稅別免稅)
     *
     *  1.當 TaxType =9 混合應稅與免稅或零稅率時,才須提供此欄位。
     *  2.純數字,為該發票中課稅別免稅之銷售額。
     *  3.銷售額計算方式請務必與公司財會人員進行確認。
     */
    public InvoiceIssue setAmtFree(int amtFree) {
        return setParam("AmtFree", "" + amtFree);
    }

    /**
     * 稅額
     *  1.純數字,為發票稅額。
     *  2.稅額計算方式請務必與公司財會人員進行確認。
     */
    public InvoiceIssue setTaxAmt(int taxAmt) {
        return setParam("TaxAmt", "" + taxAmt);
    }

    /**
     * 發票金額
     *  1.純數字,為發票總金額(含稅)。
     *  2.銷售額+稅額需等於發票金額。
     */
    public InvoiceIssue setTotalAmt(int totalAmt) {
        return setParam("TotalAmt", "" + totalAmt);
    }

    /**
     * 商品名稱
     *
     *  多項商品時,商品名稱以 | 分隔。 例:ItemName=”商品一|商品二”
     */
    public InvoiceIssue setItemName(String itemName) {
        return setParam("ItemName", itemName);
    }

    /**
     * 商品數量
     *
     *  1.純數字。
     *  2.多項商品時,商品數量以 | 分隔。 例:ItemCount =”1|2”
     */
    public InvoiceIssue setItemCount(String itemCount) {
        return setParam("ItemCount", itemCount);
    }

    /**
     * 商品單位
     *
     *  1.內容如:個、件、本、張.....。
     *  2.多項商品時,商品單位以 | 分隔。 例:ItemUnit =”個|本”
     */
    public InvoiceIssue setItemUnit(String itemUnit) {
        return setParam("ItemUnit", itemUnit);
    }

    /**
     * 商品單價
     *
     *  1.純數字。
     *  2.Category=B2B時, 此參數金額為未稅金額。
     *  3.Category=B2C 時,此參數金額為含稅金額。
     *  4.多項商品時,商品單價以 | 分隔。 例:ItemPrice =”200|100”
     */
    public InvoiceIssue setItemPrice(String itemPrice) {
        return setParam("ItemPrice", itemPrice);
    }

    /**
     * 商品小計
     *
     *  1.純數字。
     *  2.計算方式為:數量 * 單價 = 小計。
     *  3.Category=B2B 時,此參數金額為未稅金額。
     *  4.Category=B2C 時,此參數金額為含稅金額。
     *  5.多項商品時,商品小計以 | 分隔。 例:ItemAmt =”200|200”
     */
    public InvoiceIssue setItemAmt(String itemAmt) {
        return setParam("ItemAmt", itemAmt);
    }

    /**
     * 商品課稅別
     *
     *  1.當 TaxType =9 混合應稅與免稅或零稅率時,才須提供此欄位。
     *  2.課稅別為混合應稅與免稅或零稅率時,需 區分各項商品之課稅別,課稅別代號如下:
     *      1=應稅
     *      2=零稅率
     *      3=免稅
     *  3.多項商品時,商品課稅別以| 分隔。 例:ItemRate =”1|1”。
     */
    public InvoiceIssue setItemTaxType(String itemTaxType) {
        return setParam("ItemTaxType", itemTaxType);
    }

    /**
     * 備註
     *
     *  1.發票備註,字數限 71 字。
     *  2.依據統一發票使用辦法第三章、第 9 條、第四項規定:營業人銷售貨物或勞務與持用簽帳卡之買受人者,
     *    應於開立統一發票時,於發票備註欄載明簽帳卡號末四碼。例:信用卡末四碼:1234
     */
    public InvoiceIssue setComment(String comment) {
        return setParam("Comment", comment);
    }

    /**
     * 語義式建構方法: 開立 B2C 發票，加上買受人名稱
     */
    public InvoiceIssue b2c(String buyerName) {
        return setCategory(B2C).setBuyerName(buyerName);
    }

    /**
     * 語義式建構方法: 開立 B2C 發票，加上買受人名稱與信箱
     */
    public InvoiceIssue b2c(String buyerName, String buyerEmail) {
        return b2c(buyerName).setBuyerEmail(buyerEmail);
    }

    /**
     * 語義式建構方法: 開立 B2C 發票，加上買受人名稱與信箱跟地址
     */
    public InvoiceIssue b2c(String buyerName, String buyerEmail, String buyerAddress) {
        return b2c(buyerName, buyerEmail).setBuyerAddress(buyerAddress);
    }

    /**
     * 語義式建構方法: 開立 B2B 發票，加上買受人統編、名稱與地址
     */
    public InvoiceIssue b2b(String buyerUBN, String buyerName, String buyerAddress) {
        return setCategory(B2B)
                .setBuyerUBN(buyerUBN)
                .setBuyerName(buyerName)
                .setBuyerAddress(buyerAddress)
                .withPaper();
    }

    /**
     * 語意式建構方法: 索取紙本發票
     */
    public InvoiceIssue withPaper() {
        return setPrintFlag(Y);
    }

    /**
     * 語意式建構方法: 設定發票載具
     */
    public InvoiceIssue withCarrier(CarrierType type, String num) {
        return setPrintFlag(N)
                .setCarrierType(type.id())
                .setCarrierNum(num);
    }

    /**
     * 語意式建構方法: 捐贈發票
     */
    public InvoiceIssue donate(String loveCode) {
        return setPrintFlag(N)
                .setLoveCode(loveCode);
    }

    /**
     * 語意式建構方法: 設定課稅方式
     */
    public InvoiceIssue tax(TaxType type, int rate) {
        return setTaxType(type).setTaxRate(rate);
    }

    /**
     * 語意式建構方法: 應稅，使用一般稅率
     */
    public InvoiceIssue duty() {
        return duty(NORMAL_TAX_RATE);
    }

    /**
     * 語意式建構方法: 應稅
     */
    public InvoiceIssue duty(int rate) {
        return tax(DUTY, rate);
    }

    /**
     * 語意式建構方法: 免稅
     */
    public InvoiceIssue dutyFree() {
        return tax(FREE, 0);
    }

    /**
     * 語意式建構方法: 零稅率
     */
    public InvoiceIssue dutyZero() {
        return tax(ZERO, 0);
    }

    /**
     * 語義式建構方法: 加上一組商品明細
     * @param count 數量
     * @param unit 單位
     * @param name 名稱
     * @param pricePerUnit 單價
     */
    public InvoiceIssue addItem(int count, String unit, String name, int pricePerUnit) {
        itemList.addItem(count, unit, name, pricePerUnit);
        return setItemCount(itemList.getListCountString())
                .setItemUnit(itemList.getListUnitString())
                .setItemName(itemList.getListNameString())
                .setItemPrice(itemList.getListPriceString())
                .setItemAmt(itemList.getListAmtString())
                .calcAmt();
    }

    /**
     * 語義式建構方法: 設定計算時商品不含稅
     */
    public InvoiceIssue taxExcluded() {
        itemList.setTaxIncluded(false);
        return this;
    }

    /**
     * 語義式建構方法: 根據商品明細計算 發票金額、稅額、銷售額
     */
    public InvoiceIssue calcAmt() {
        if (itemList.size() == 0) {
            return this;
        }

        return setTotalAmt(itemList.getTotalAmt())
                .setTaxAmt(itemList.getTaxAmt())
                .setAmt(itemList.getAmt());
    }

    /**
     * 語義式建構方法: 等待觸發開立發票
     */
    public InvoiceIssue recheck() {
        return setStatus(RECHECK);
    }

    /**
     * 語義式建構方法: 即時開立發票
     */
    public InvoiceIssue receipt() {
        return setStatus(IMMEDIATE);
    }

    /**
     * 語義式建構方法: 預約開立發票
     */
    public InvoiceIssue receipt(Date createDate) {
        return setStatus(RESERVE)
                .setCreateStatusTime(new SimpleDateFormat(CREATE_STATUS_TIME_FORMAT).format(createDate));
    }

    /**
     * 發票種類
     */
    public enum Category {
        B2B, B2C
    }

    /**
     * 是否索取紙本發票
     */
    public enum PrintFlag {
        Y, N
    }

    /**
     * 載具類型
     */
    public enum CarrierType {

        /**
         * 手機
         */
        MOBILE("0"),

        /**
         * 自然人憑證條碼
         */
        CITIZEN("1"),

        /**
         * 智付寶
         */
        Pay2Go("2");

        String id;

        CarrierType(String id) {
            this.id = id;
        }

        public String id() {
            return id;
        }

    }

    /**
     * 課稅別
     */
    public enum TaxType {

        /**
         * 應稅
         */
        DUTY("1"),

        /**
         * 零稅
         */
        ZERO("2"),

        /**
         * 免稅
         */
        FREE("3"),

        /**
         * 混合
         */
        MIXED("9");

        String id;

        TaxType(String id) {
            this.id = id;
        }

        public String id() {
            return id;
        }

    }

    /**
     * 開立發票方式
     */
    public enum Status {

        /**
         * 等待觸發開立發票
         */
        RECHECK("0"),

        /**
         * 即時開立發票
         */
        IMMEDIATE("1"),

        /**
         * 預約開立發票
         */
        RESERVE("3");

        String id;

        Status(String id) {
            this.id = id;
        }

        public String id() {
            return id;
        }
    }

}

