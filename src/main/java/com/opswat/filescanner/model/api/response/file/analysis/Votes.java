package com.opswat.filescanner.model.api.response.file.analysis;

import com.google.gson.annotations.SerializedName;

public class Votes{

	@SerializedName("up")
	private int up;

	@SerializedName("down")
	private int down;
}