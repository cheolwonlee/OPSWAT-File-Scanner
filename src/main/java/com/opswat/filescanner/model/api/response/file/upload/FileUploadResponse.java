package com.opswat.filescanner.model.api.response.file.upload;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FileUploadResponse{

	@SerializedName("sha1")
	private String sha1;

	@SerializedName("in_queue")
	private int inQueue;

	@SerializedName("sha256")
	private String sha256;

	@SerializedName("data_id")
	private String dataId;

	@SerializedName("queue_priority")
	private String queuePriority;

	@SerializedName("status")
	private String status;
}