package com.opswat.filescanner.model.api.response.file.analysis;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProcessInfo{

	@SerializedName("result")
	private String result;

	@SerializedName("blocked_reason")
	private String blockedReason;

	@SerializedName("profile")
	private String profile;

	@SerializedName("progress_percentage")
	private int progressPercentage;
}