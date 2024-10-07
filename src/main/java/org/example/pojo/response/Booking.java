package org.example.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Booking{

	@JsonProperty("firstname")
	private String firstName;

	@JsonProperty("additionalneeds")
	private String additionalNeeds;

	@JsonProperty("bookingdates")
	private Bookingdates bookingDates;

	@JsonProperty("totalprice")
	private int totalPrice;

	@JsonProperty("depositpaid")
	private boolean depositPaid;

	@JsonProperty("lastname")
	private String lastName;
}