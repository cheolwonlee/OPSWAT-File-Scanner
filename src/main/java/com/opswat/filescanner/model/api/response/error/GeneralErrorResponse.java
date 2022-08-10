package com.opswat.filescanner.model.api.response.error;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GeneralErrorResponse{

	@SerializedName("error")
	private Error error;
}