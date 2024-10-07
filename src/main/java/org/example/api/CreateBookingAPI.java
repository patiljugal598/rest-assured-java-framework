package org.example.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.http.BaseAPI;
import org.example.pojo.request.CreateRequestBooking;

import java.util.Map;

import static org.example.constants.ApiPaths.CREATE_BOOKING;

public class CreateBookingAPI extends BaseAPI {

    public CreateBookingAPI(){
        super();
        super.logAllRequestData().logAllResponseData().setContentType(ContentType.JSON);
    }

    public Response createNewBooking(Map<String, Object> createBookingPayload){
        return getCreateBookingApiResponse(createBookingPayload);
    }

    public Response createNewBooking(CreateRequestBooking createBookingPayload){
        return getCreateBookingApiResponse(createBookingPayload);
    }

    @Step("Prepare data and send post request")
    private Response getCreateBookingApiResponse(Object createBookingPayload){
        super.setBasePath(CREATE_BOOKING.getApiPath());
        super.setRequestBody(createBookingPayload);
        return super.sendRequest(CREATE_BOOKING.getHttpMethodType());
    }
}
