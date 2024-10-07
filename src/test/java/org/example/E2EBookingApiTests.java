package org.example;

import org.example.api.CreateBookingAPI;
import org.example.api.DeleteBookingAPI;
import org.example.api.GetBookingAPI;
import org.example.api.UpdateBookingAPI;
import org.example.pojo.request.BookingDates;
import org.example.pojo.request.CreateRequestBooking;
import org.example.utils.TestDataHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class E2EBookingApiTests {


    @Test
    public void e2eBookingTest() {
        DeleteBookingAPI deleteBookingAPI = new DeleteBookingAPI();
        CreateBookingAPI createBookingAPI = new CreateBookingAPI();
        var faker = TestDataHelper.getFaker();

        // create booking
        var createBookingApiResponse = createBookingAPI.createNewBooking(getData(faker.name().firstName(), faker.name().lastName(), Integer.parseInt(faker.regexify("[1-9]{3}")))).then().assertThat().statusCode(201)
                .body("bookingid", is(not(equalTo(0))));

        int bookingId = createBookingApiResponse.extract().jsonPath().getInt("bookingid");

        // get booking
        GetBookingAPI getBookingAPI = new GetBookingAPI();
        var getBookingIdsResponse = getBookingAPI.getBookingById(bookingId).then().assertThat().statusCode(200);

        // update booking
        UpdateBookingAPI updateBookingAPI = new UpdateBookingAPI();
        var updateBookingApiResponse = updateBookingAPI.updateBooking(getData(faker.name().firstName(),  faker.name().lastName(), Integer.parseInt(faker.regexify("[1-9]{3}"))), bookingId, "admin", "password123").then().assertThat().statusCode(200)
                .body("bookingid", is(not(equalTo(0))));

        // delete booking
        var deleteBookingApiResponse = deleteBookingAPI.deleteBookingById(bookingId, "admin", "password123").then().assertThat().statusCode(201);
    }

    private CreateRequestBooking getData(String firstname, String lastname, int price) {
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
