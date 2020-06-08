package com.ahmedsoftware.carrental;


import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8081)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {
        "com.ahmedsoftware:fraud-service:0.0.1-SNAPSHOT:stubs" }, stubsMode = StubRunnerProperties.StubsMode.LOCAL,minPort = 8083,maxPort = 8083)
class CarRentalApplicationTests {
    /*int testIntegrationPort=8083;
    
    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadStub("com.ahmedsoftware","fraud-service","0.0.1-SNAPSHOT","stubs")
            //.downloadStub("com.ahmedsoftware:fraud-service:0.0.1-SNAPSHOT:stubs")
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL)
            .minPort(this.testIntegrationPort)
            .maxPort(this.testIntegrationPort);
    
    
    @Test
    public void test_should_return_path_of_stup(){
        assertFalse(stubRunnerRule.findAllRunningStubs().getAllServicesNames().isEmpty()); //assertion fails
        stubRunnerRule.findStubUrl("com.ahmedsoftware:fraud-service");
    }*/
    
	@Test
    public void test_should_return_all_frauds(){
     
	    String json = "[\"ahmed\",\"ali\"]";
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/fraud")).willReturn(WireMock.aResponse().withBody(json).withStatus(201)));
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8081/fraud", String.class);
        BDDAssertions.then(entity.getStatusCode().value()).isEqualTo(201);
        BDDAssertions.then(entity.getBody()).isEqualTo(json);
        
    }
    
   
    
    @Test
    public void test_should_return_all_frauds_integration(){
        
        String json = "[\"ahmed\",\"ali\"]";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8083/fraud", String.class);
        BDDAssertions.then(entity.getStatusCode().value()).isEqualTo(201);
        BDDAssertions.then(entity.getBody()).isEqualTo(json);
        
    }

}
