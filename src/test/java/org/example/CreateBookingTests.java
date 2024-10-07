package org.example;

import org.example.api.CreateBookingAPI;
import org.example.api.DeleteBookingAPI;
import org.example.pojo.request.BookingDates;
import org.example.pojo.request.CreateRequestBooking;
import org.example.utils.TestDataHelper;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class CreateBookingTests {



    @Test(description = "Create a booking and validate status code",dataProvider = "getData")
    public void createAndValidateStatusCode(String firstname, String lastname, int amount){
        CreateBookingAPI createBookingAPI =new CreateBookingAPI();
        DeleteBookingAPI deleteBookingAPI = new DeleteBookingAPI();


        var createBookingApiResponse  = createBookingAPI.createNewBooking(getCreateBookingData(firstname,lastname,amount)).then().assertThat().statusCode(200)
                .body("bookingid", is(not(equalTo(0))));

        int bookingId = createBookingApiResponse.extract().jsonPath().getInt("bookingid");
        var deleteBookingApiResponse = deleteBookingAPI.deleteBookingById(bookingId, "admin", "password123").then().assertThat().statusCode(201);

    }

    private CreateRequestBooking getCreateBookingData(String firstname, String lastname, int price){
        CreateRequestBooking createRequestBooking = new CreateRequestBooking();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        createRequestBooking.setBookingDates(bookingDates);
        createRequestBooking.setAdditionalNeeds("Breakfast");
        createRequestBooking.setFirstName(firstname);
        createRequestBooking.setDepositPaid(false);
        createRequestBooking.setLastName(lastname);
        createRequestBooking.setTotalPrice(price);
        return createRequestBooking;
    }

    @DataProvider(parallel = true)
    public Object[][] getData(){
        var faker = TestDataHelper.getFaker();
           return  new Object[][] {{faker.name().firstName(),faker.name().lastName(),Integer.parseInt(faker.regexify("[1-9]{3}"))}
           ,{faker.name().firstName(),faker.name().lastName(),Integer.parseInt(faker.regexify("[1-9]{3}"))}
           ,{faker.name().firstName(),faker.name().lastName(),Integer.parseInt(faker.regexify("[1-9]{3}"))}};
    }

}
