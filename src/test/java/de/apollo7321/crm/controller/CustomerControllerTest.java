package de.apollo7321.crm.controller;

import de.apollo7321.crm.dto.CreateCustomerDTO;
import de.apollo7321.crm.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerDTOWrapper{

    private List<CustomerDTO> content;

    public void setContent(List<CustomerDTO> content) {
        this.content = content;
    }

    public List<CustomerDTO> getContent() {
        return content;
    }
}

@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    private static final String url = "/customer";

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:18.1-alpine");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllCustomers() {
        var params = new HashMap<String, String>();
        params.put("page", "0");
        params.put("size", "10");
        var getAllUsersResponse = restTemplate.getForEntity(url, CustomerDTOWrapper.class);
        assertEquals(HttpStatus.OK, getAllUsersResponse.getStatusCode());

        var allUsersBody = getAllUsersResponse.getBody();
        assertNotNull(allUsersBody);

        var isHansWurstInResponse = allUsersBody.getContent().contains(new CustomerDTO("Hans", "Wurst"));
        assertTrue(isHansWurstInResponse);
    }

    @Test
    void createCustomerSucceeds() {
        var firstName = "Max";
        var lastName = "Mustermann";

        // create the user
        var newCustomer = new CreateCustomerDTO(firstName, lastName);
        var createResponse = restTemplate.postForEntity(url, newCustomer, CustomerDTO.class);

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertEquals(firstName, createResponse.getBody().firstName());
        assertEquals(lastName, createResponse.getBody().lastName());

        // check if the user exists
        var getAllUsersResponse = restTemplate.getForEntity(url, CustomerDTOWrapper.class);
        assertEquals(HttpStatus.OK, getAllUsersResponse.getStatusCode());

        var allUsersBody = getAllUsersResponse.getBody();
        assertNotNull(allUsersBody);

        var isMaxMustermannInResponse = allUsersBody.getContent().contains(new CustomerDTO("Hans", "Wurst"));
        assertTrue(isMaxMustermannInResponse);
    }

    @ParameterizedTest
    @CsvSource({",Mustermann", "Max,", ","})
    void createCustomerFailForBlankNames(String firstName, String lastName) {
        var newCustomer = new CreateCustomerDTO(firstName, lastName);
        var createResponse = restTemplate.postForEntity(url, newCustomer, CustomerDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, createResponse.getStatusCode());
    }
}