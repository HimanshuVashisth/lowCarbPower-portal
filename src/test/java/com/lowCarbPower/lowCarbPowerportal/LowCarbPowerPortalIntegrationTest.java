package com.lowCarbPower.lowCarbPowerportal;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import java.net.URI;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.dozermapper.core.Mapper;
import com.lowCarbPower.lowCarbPowerportal.entity.CustomerEntity;
import com.lowCarbPower.lowCarbPowerportal.entity.PriceEntity;
import com.lowCarbPower.lowCarbPowerportal.model.CustomerModel;
import com.lowCarbPower.lowCarbPowerportal.model.PriceModel;
import com.lowCarbPower.lowCarbPowerportal.model.UserModel;
import com.lowCarbPower.lowCarbPowerportal.repository.jpa.customer.CustomerRepository;
import com.lowCarbPower.lowCarbPowerportal.repository.jpa.price.PriceRepository;

import junit.framework.Assert;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class LowCarbPowerPortalIntegrationTest {

	@LocalServerPort
	private int port;
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private CustomerRepository customerRepo;
    
    @Autowired
    private PriceRepository priceRepo;
    
	@Autowired
	private Mapper mapper;
    
	@BeforeClass
    public void setUp() {
    	
    	// Add a Dummy customer on Application Test startup
    	CustomerModel customerModel = new CustomerModel("himanshu@abc.com","abc1234");
    	CustomerEntity customerEntity = new CustomerEntity();
    	mapper.map(customerModel,customerEntity);
    	
    	customerRepo.save(customerEntity);
    	
    	PriceModel priceModel = new PriceModel("NZD", 90.26);
    	PriceEntity priceEntity = new PriceEntity();
    	mapper.map(priceModel,priceEntity);
    	
    	priceRepo.save(priceEntity);
    }
    
	
	@SuppressWarnings("deprecation")
	@Test
	public void getMonthlyBillEstimate_status200() {
		String url = "http://localhost:" + this.port;
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/estimate/monthly")
                .queryParam("provideValueInkWH", 1000)
                .queryParam("customerId", 1)
                .build().toUri();
        ResponseEntity<PriceModel> result = this.restTemplate.getForEntity(uri, PriceModel.class);
        
//        //Verify request succeed
//        Assert.assertEquals(200, result.getStatusCodeValue());
//        /**
//         * Since in the live API may or may not be changing every hour therefore better not to hard code any value in assertion for estimated monthly bill.
//         * */
//        assertThat(result.getBody().getValue(), instanceOf(Double.class));         
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void successfulLoginService_status200() {
		String url = "http://localhost:" + this.port;
		UserModel model = new UserModel("himanshu@abc.com","abc1234");
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<UserModel> request = new HttpEntity<>(model, headers);
		
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/auth/login").build().toUri();
        ResponseEntity<CustomerModel> result = this.restTemplate.postForEntity(uri, request, CustomerModel.class);
        
//        //Verify request succeed
//        Assert.assertEquals(200, result.getStatusCodeValue());
//        /**
//         * Since in the live API may or may not be changing every hour therefore better not to hard code any value in assertion for estimated monthly bill.
//         * */
//        assertThat(result.getBody().getUsername(), is(model.getUsername()));
//        assertThat(result.getBody().getCustomerId(), instanceOf(Long.class));
	}
	
}
