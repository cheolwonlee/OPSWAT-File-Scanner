package com.opswat.filescanner.model.api.response.file.analysis;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class ScanResults{

	@SerializedName("scan_all_result_a")
	private String scanAllResultA;

	@SerializedName("start_time")
	private String startTime;

	@SerializedName("scan_details")
	private Map<String,ScanEngine> scanDetails;

	@SerializedName("scan_all_result_i")
	private int scanAllResultI;

	@SerializedName("progress_percentage")
	private int progressPercentage;

	@SerializedName("total_time")
	private int totalTime;

	@SerializedName("total_detected_avs")
	private int totalDetectedAvs;

	@SerializedName("total_avs")
	private int totalAvs;
}