package com.eHanlin.api.invoice.util

import spock.lang.Specification

class HttpInvokerSpec extends Specification {

    def "回應是JSON字串"() {
        given:
        def invoker = new HttpInvoker()

        when:
        def responseBody = invoker.post("https://cinv.ezpay.com.tw/Api/invoice_issue", [:])

        then:
        responseBody instanceof String
        responseBody == "{\"Status\":\"KEY10010\",\"Message\":\"\\u5546\\u5e97\\u4ee3\\u865f\\u7a7a\\u767d\",\"Result\":[]}"
    }

}