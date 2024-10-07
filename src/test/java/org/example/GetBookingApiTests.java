package org.example;

import org.example.api.CreateBookingAPI;
import org.example.api.DeleteBookingAPI;
import org.example.api.GetBookingAPI;
import org.example.pojo.request.BookingDates;
import org.example.pojo.request.CreateRequestBooking;
import org.example.utils.TestDataHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class GetBookingApiTests {

    private GetBookingAPI getBookingAPI;

    @BeforeClass
    public void initApi(){
        this.getBookingAPI= new GetBookingAPI();
    }

    @Test(description = "Basic status code check for get booking Ids API.")
    public void validateStatusCodeForGetBookingId(){
        GetBookingAPI getBookingAPI = new GetBookingAPI();
        var getBookingIdsResponse = getBookingAPI.getBookingIds().then().assertThat().statusCode(200);
    }

    @Test(description = "Basic status code check for get booking by Ids API.")
    public void validateStatusCodeForGetBookingBYId(){
        CreateBookingAPI createBookingAPI =new CreateBookingAPI();
        DeleteBookingAPI deleteBookingAPI = new DeleteBookingAPI();

        var faker=  TestDataHelper.getFaker();
        int bookingId = createBookingAPI.createNewBooking(getCreateBookingData(faker.name().firstName(),faker.name().lastName(),Integer.parseInt(faker.regexify("[1-9]{3}")))).then().assertThat().statusCode(200)
                .body("bookingid", is(not(equalTo(0)))).extract().jsonPath().getInt("bookingid");


        GetBookingAPI getBookingAPI = new GetBookingAPI();
        var getBookingIdsResponse = getBookingAPI.getBookingById(bookingId).then().assertThat().statusCode(200);

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
}
