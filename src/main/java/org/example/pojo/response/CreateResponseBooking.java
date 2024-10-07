package org.example.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateResponseBooking{

	@JsonProperty("booking")
	private Booking booking;

	@JsonProperty("bookingid")
	private int bookingId;
}