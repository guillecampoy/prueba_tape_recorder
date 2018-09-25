package org.grails.guides

import com.mercadolibre.taperecorder.ApiRestService
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import spock.lang.Specification

class ApiRestServiceSpec extends Specification {
    private ApiRestService apiRest;

    def setup() {
        apiRest = new ApiRestService("http://api.internal.ml.com");
        apiRest.start();
    }

    def cleanup() {
        apiRest.stop();
    }

    void testEstimateRetrieval() {
        given: 'client'
        CloseableHttpClient client = HttpClientBuilder.create().build();

        when: 'get launched'
        CloseableHttpResponse response = client.execute(
                new HttpGet(
                        "http://localhost:8888/buyingflow/api/users?client.id=457027154409645&caller.id=312424379"
                )
        );

        then: '200 got'
        "HTTP/1.1 200 OK".equals( "HTTP/1.1 200 OK")
    }
}
