package com.opswat.filescanner.model.api.response.file.analysis;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class FileAnalysisResponse{

	@SerializedName("scan_results")
	private ScanResults scanResults;

	@SerializedName("scan_result_history_length")
	private int scanResultHistoryLength;

	@SerializedName("sandbox")
	private boolean sandbox;

	@SerializedName("data_id")
	private String dataId;

	@SerializedName("process_info")
	private ProcessInfo processInfo;

	@SerializedName("additional_info")
	private List<Object> additionalInfo;

	@SerializedName("rest_version")
	private String restVersion;

	@SerializedName("file_id")
	private String fileId;

	@SerializedName("file_info")
	private FileInfo fileInfo;

	@SerializedName("stored")
	private boolean stored;

	@SerializedName("appinfo")
	private boolean appinfo;

	@SerializedName("private_processing")
	private int privateProcessing;

	@SerializedName("votes")
	private Votes votes;

	@SerializedName("share_file")
	private int shareFile;

	@SerializedName("scan_with")
	private String scanWith;
}