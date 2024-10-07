package org.example.api;

import io.qameta.allure.Step;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.http.BaseAPI;
import org.example.pojo.request.CreateRequestBooking;

import java.util.Map;

import static org.example.constants.ApiPaths.CREATE_BOOKING;
import static org.example.constants.ApiPaths.UPDATE_BOOKING;

public class UpdateBookingAPI extends BaseAPI {

    public UpdateBookingAPI(){
        super();
        super.logSpecificRequestDetail(LogDetail.BODY).logAllResponseData().setContentType(ContentType.JSON);
    }

    public Response updateBooking(Map<String, Object> createBookingPayload, int id, String username, String password){
        return getCreateBookingApiResponse(createBookingPayload, id,username,password);
    }

    @Step("Update booking with id {id}")
    public Response updateBooking(CreateRequestBooking createBookingPayload, int id, String username, String password){
        return getCreateBookingApiResponse(createBookingPayload, id,username,password);
    }

    @Step("Create booking with id {id}")
    private Response getCreateBookingApiResponse(Object createBookingPayload, int id, String username, String password){
        super.setBasePath(UPDATE_BOOKING.getApiPath());
        super.setRequestBody(createBookingPayload);
        super.setPathParam("bookingId",id);
        super.setBasicAuth(username,password);
        return super.sendRequest(UPDATE_BOOKING.getHttpMethodType());
    }
}
