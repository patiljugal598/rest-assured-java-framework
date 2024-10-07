package org.example.api;

import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.example.constants.ApiPaths;
import org.example.http.BaseAPI;

import static io.restassured.http.Method.GET;
import static org.example.constants.ApiPaths.GET_BOOKING;
import static org.example.constants.ApiPaths.GET_BOOKING_IDS;

public class GetBookingAPI extends BaseAPI {
    public GetBookingAPI() {
        super();
        super.logAllRequestData().logAllResponseData();
    }

    @Step("Get Booking by ids")
    public Response getBookingIds(){
        super.setBasePath(GET_BOOKING_IDS.getApiPath());
        return super.sendRequest(GET_BOOKING_IDS.getHttpMethodType());
    }

    @Step("Get Booking by id {id}")
    public Response getBookingById(int id){
        super.setBasePath(GET_BOOKING.getApiPath());
        super.setPathParam("bookingId",id);
        return super.sendRequest(GET_BOOKING.getHttpMethodType());
    }
}
