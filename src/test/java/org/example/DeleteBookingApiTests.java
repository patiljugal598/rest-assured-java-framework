package org.example;

import io.qameta.allure.Step;
import org.example.api.CreateBookingAPI;
import org.example.api.DeleteBookingAPI;
import org.example.api.UpdateBookingAPI;
import org.example.pojo.request.BookingDates;
import org.example.pojo.request.CreateRequestBooking;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class DeleteBookingApiTests {

    private DeleteBookingAPI deleteBookingAPI;
    private CreateBookingAPI createBookingAPI;

    @BeforeClass
    public void initApi() {
        this.deleteBookingAPI = new DeleteBookingAPI();
        this.createBookingAPI = new CreateBookingAPI();
    }

    @Test
    public void deleteAndValidateStatusCode() {
        var createBookingApiResponse = this.createBookingAPI.createNewBooking(getData("Jugal", "Patil", 121)).then().assertThat().statusCode(200)
                .body("bookingid", is(not(equalTo(0))));

        int bookingId = createBookingApiResponse.extract().jsonPath().getInt("bookingid");

        var deleteBookingApiResponse = this.deleteBookingAPI.deleteBookingById(bookingId, "admin", "password123").then().assertThat().statusCode(201);
                //.body("bookingid", is(not(equalTo(0))));
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
