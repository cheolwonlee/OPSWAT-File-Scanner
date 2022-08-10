package com.opswat.filescanner.model.api.response.error;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class Error{

	@SerializedName("code")
	private int code;

	@SerializedName("messages")
	private List<String> messages;
}