package org.example;

import org.example.api.CreateBookingAPI;
import org.example.api.DeleteBookingAPI;
import org.example.api.UpdateBookingAPI;
import org.example.pojo.request.BookingDates;
import org.example.pojo.request.CreateRequestBooking;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class UpdateBookingApiTests {

    @Test
    public void updateAndValidateStatusCode() {
        UpdateBookingAPI updateBookingAPI = new UpdateBookingAPI();
        CreateBookingAPI createBookingAPI = new CreateBookingAPI();
        DeleteBookingAPI deleteBookingAPI = new DeleteBookingAPI();

        var createBookingApiResponse = createBookingAPI.createNewBooking(getData("Jugal", "Patil", 121)).then().assertThat().statusCode(200)
                .body("bookingid", is(not(equalTo(0))));

        int bookingId = createBookingApiResponse.extract().jsonPath().getInt("bookingid");

        var updateBookingApiResponse = updateBookingAPI.updateBooking(getData("Tejas", "Patil", 200), bookingId, "admin", "password123").then().assertThat().statusCode(200)
                .body("bookingid", is(not(equalTo(0))));

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
