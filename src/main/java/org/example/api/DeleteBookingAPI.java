package org.example.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.http.BaseAPI;

import static org.example.constants.ApiPaths.DELETE_BOOKING;
import static org.example.constants.ApiPaths.GET_BOOKING;

public class DeleteBookingAPI extends BaseAPI {

    public DeleteBookingAPI(){
        super();
        super.logAllRequestData().logAllResponseData();
    }
    @Step("Delete and validate Status code")
    public Response deleteBookingById(int id, String username, String password){
        super.setBasePath(DELETE_BOOKING.getApiPath());
        super.setPathParam("bookingId",id);
        super.setBasicAuth(username, password);
        return super.sendRequest(DELETE_BOOKING.getHttpMethodType());
    }
}
