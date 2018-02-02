package com.eHanlin.api.invoice.util

import spock.lang.Specification

class HttpInvokerSpec extends Specification {

    def "回應是JSON字串"() {
        given:
        def invoker = new HttpInvoker()

        when:
        def responseBody = invoker.post("https://cinv.pay2go.com/API/x", [:])

        then:
        responseBody instanceof String
        responseBody == "result"
    }

}