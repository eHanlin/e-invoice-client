package com.eHanlin.api.invoice.pay2go

import spock.lang.*
import com.eHanlin.api.invoice.pay2go.api.InvoiceIssue
import com.eHanlin.api.invoice.pay2go.model.InvoiceIssueResult

class Pay2GoInvoiceSpec extends Specification {

    @Shared pay2GoInvoice = new Pay2GoInvoice(
            "https://cinv.pay2go.com/API/",
            "3673959",
            "LjScrq160VKUN4ZVSeaNtKSgBPRnV3YJ",
            "fuk7A4D3JMXDeIHm"
    )

    InvoiceIssue api

    def setup() {
        api = new InvoiceIssue()
                .setMerchantOrderNo("_pay2go_invoice_issue_")
                .setBuyerName("魯小")
                .setCategory(InvoiceIssue.Category.B2C)
                .setTaxType(InvoiceIssue.TaxType.DUTY)
                .setTaxRate(5)
                .setTaxAmt(476)
                .setAmt(9524)
                .setTotalAmt(10000)
                .setPrintFlag(InvoiceIssue.PrintFlag.Y)
                .setItemName("雲端學院e名師")
                .setItemCount("1")
                .setItemUnit("個")
                .setItemPrice("10000")
                .setItemAmt("10000")
                .setStatus(InvoiceIssue.Status.IMMEDIATE)
                .setTimeStamp(new Date())
    }

    @Unroll
    def "智付寶開立發票參數缺少 #param 回應訊息 #message"() {
        when:
        api.setParam(param, null)
        def pay2goResponse = pay2GoInvoice.issue(api)

        then:
        pay2goResponse.getMessage() == message

        where:
        param             || message
        "MerchantOrderNo" || "資料不齊全MerchantOrderNo"
        "BuyerName"       || "資料不齊全BuyerName"
        "Category"        || "資料不齊全Category"
        "TaxType"         || "資料不齊全TaxType"
        "TaxRate"         || "資料不齊全TaxRate"
        "TaxAmt"          || "請確認發票金額是否正確 (銷售金額的總合 + 稅金 = 發票金額)"
        "Amt"             || "欄位資料空白-Amt"
        "TotalAmt"        || "資料不齊全TotalAmt"
        "PrintFlag"       || "資料不齊全PrintFlag"
        "ItemName"        || "資料不齊全ItemName"
        "ItemCount"       || "資料不齊全ItemCount"
        "ItemUnit"        || "資料不齊全ItemUnit"
        "ItemPrice"       || "資料不齊全ItemPrice"
        "ItemAmt"         || "資料不齊全ItemAmt"
        "Status"          || "資料不齊全Status"
        "TimeStamp"       || "資料不齊全TimeStamp"
    }

    def "開立發票"() {
        given:
        def merchantOrderNo = "_pay2go_invoice_issue_0_"
        def api = new InvoiceIssue(merchantOrderNo)
                .b2c("魯小", "rodick@ehanlin.com.tw")
                .addItem(1, "個", "含稅e名師", 10000)
                .receipt()

        when:
        Pay2GoResponse<InvoiceIssueResult> response = pay2GoInvoice.issue(api)

        then:
        with(response) {
            getStatus() == "SUCCESS"
            getMessage() in ["發票開立成功", "開立成功(此發票已重覆開立)"]
            check()
        }

        with(response.getResult()) {
            getMerchantOrderNo() == merchantOrderNo
        }
    }

    def "測試param被呼叫"() {
        setup:
        InvoiceIssue api = Mock()

        when:
        pay2GoInvoice.issue(api)

        then:
        1 * api.param() >> "*"
    }

}
