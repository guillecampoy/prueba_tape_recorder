package org.grails.guides

import com.mercadolibre.taperecorder.ApiRestService
import com.mercadolibre.taperecorder.CassetteRecording
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.StringEntity
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

    void testPayments_info() {
        given: 'client'

        CloseableHttpClient client = HttpClientBuilder.create().build();

        //acá o podemos hacer más prolijo y parametrizarlo
        URI uri = new URIBuilder("http://localhost:8888/buyingflow/api/payment_options_info?client.id=457027154409645&caller.id=312424379&status=active")
                .build()

        String bodyToPost = "{\"items_to_buy\":[{\"item_id\":\"MLA677168828\",\"quantity\":1,\"unit_type\":null,\"variation_id\":null}]}"

        HttpPost request = new HttpPost(uri)

        request.addHeader("Content-Type", "application/json")
        request.setEntity(new StringEntity(bodyToPost, "UTF-8"));

        when: 'post launched'
        CloseableHttpResponse response = client.execute(request)

        then: '200 got'
        "HTTP/1.1 200 OK".equals( "HTTP/1.1 200 OK")
    }
}