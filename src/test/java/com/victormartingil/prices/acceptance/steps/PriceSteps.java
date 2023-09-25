package com.victormartingil.prices.acceptance.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.model.PriceRequestDto;
import org.openapitools.model.PriceResponseDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.victormartingil.prices.utils.PriceUtils.createPriceResponseDtoFromDateTable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceSteps {

    private final PriceRequestDto priceRequestDto;
    private PriceResponseDto priceResponseDtoFromApi;

    public PriceSteps() {
        this.priceRequestDto = new PriceRequestDto();
    }


    @Given("the current date is {string}")
    public void theCurrentDateIs(final String date) {
        priceRequestDto.setDate(date);
    }

    @Given("the product ID is {string}")
    public void aRequestIsMadeForProductID(final String productId) {
        priceRequestDto.setProductId(Integer.parseInt(productId));
    }

    @Given("the brand ID is {string}")
    public void aRequestIsMadeForBrandID(final String brandId) {
        priceRequestDto.setBrandId(Integer.parseInt(brandId));
    }

    @When("the request for the price is done")
    public void theRequestForThePriceIsDone() {

        final RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080" + "/api/v1/price";
        final String params = "?productId" + "=" + priceRequestDto.getProductId() + "&" +
                "brandId" + "=" + priceRequestDto.getBrandId() + "&" +
                "date" + "=" + priceRequestDto.getDate();
        final ResponseEntity<PriceResponseDto> response = restTemplate.exchange(baseUrl + params, HttpMethod.GET, null, PriceResponseDto.class);

        priceResponseDtoFromApi = response.getBody();
    }

    @Then("^the response should include the following details:$")
    public void theResponseShouldIncludeTheFollowingDetails(final DataTable dataTable) {
        final PriceResponseDto expected = createPriceResponseDtoFromDateTable(dataTable);
        assertResponse(expected, priceResponseDtoFromApi);
    }

    private void assertResponse(final PriceResponseDto expected, final PriceResponseDto response) {
        assertEquals(expected.getBrandId(), response.getBrandId());
        assertEquals(expected.getProductId(), response.getProductId());
        assertEquals(expected.getPriceList(), response.getPriceList());
        assertEquals(expected.getStartDate(), response.getStartDate());
        assertEquals(expected.getEndDate(), response.getEndDate());
        assertEquals(expected.getPrice(), response.getPrice());

    }

}
