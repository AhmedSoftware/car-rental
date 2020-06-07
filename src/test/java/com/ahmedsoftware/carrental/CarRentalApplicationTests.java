package com.ahmedsoftware.carrental;


import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
@AutoConfigureWireMock(port = 8081)
class CarRentalApplicationTests {

	@Test
    public void test_should_return_all_frauds(){
     
	    String json = "[\"ahmed\",\"ali\"]";
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/frauds")).willReturn(WireMock.aResponse().withBody(json).withStatus(200)));
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8081/frauds", String.class);
        BDDAssertions.then(entity.getStatusCode().value()).isEqualTo(200);
        BDDAssertions.then(entity.getBody()).isEqualTo(json);
        
    }

}
