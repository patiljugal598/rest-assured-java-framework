package org.example.http;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.utils.ConfigLoader;

public abstract class BaseAPI {

    private final RequestSpecification requestSpecification;
    public BaseAPI(){
        this.requestSpecification = RestAssured.given().baseUri(ConfigLoader.getInstance().getBaseURI()).filter(new AllureRestAssured());
    }

    public BaseAPI setRequestBody(Object object){
        this.requestSpecification.body(object);
        return this;
    }

    public void setBasePath(String basePath){
        this.requestSpecification.basePath(basePath);
    }

    public void setPathParam(String paramName, Object paramValue){
        this.requestSpecification.pathParam(paramName,paramValue);
    }
    public BaseAPI setContentType(ContentType contentType){
        this.requestSpecification.contentType(contentType);
        return this;
    }

    public BaseAPI setBasicAuth(String username, String password){
        this.requestSpecification.auth().preemptive().basic(username, password);
        return this;
    }

    public BaseAPI logAllRequestData(){
        this.requestSpecification.filter(new ResponseLoggingFilter());
        return this;
    }

    public BaseAPI logSpecificRequestDetail(LogDetail logDetail){
        this.requestSpecification.filter(new RequestLoggingFilter(logDetail));
        return this;
    }

    public BaseAPI logAllResponseData(){
        this.requestSpecification.filter(new ResponseLoggingFilter());
        return this;
    }

    public BaseAPI logSpecificResponseDetail(LogDetail logDetail){
        this.requestSpecification.filter(new RequestLoggingFilter(logDetail));
        return this;
    }

    protected Response sendRequest(Method methodType){
        final RequestSpecification when = this.requestSpecification.when();
        return switch (methodType){
            case GET -> when.get();
            case PUT -> when.put();
            case POST -> when.post();
            case DELETE -> when.delete();
            case PATCH -> when.patch();
            default -> throw new IllegalArgumentException("Invalid method type");
        };
    }
}
