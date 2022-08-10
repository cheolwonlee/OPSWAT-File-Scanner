package com.opswat.filescanner.model.api.response.file.analysis;

import com.google.gson.annotations.SerializedName;

public class FileInfo{

	@SerializedName("sha1")
	private String sha1;

	@SerializedName("upload_timestamp")
	private String uploadTimestamp;

	@SerializedName("sha256")
	private String sha256;

	@SerializedName("file_type_extension")
	private String fileTypeExtension;

	@SerializedName("file_type_category")
	private String fileTypeCategory;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("file_size")
	private int fileSize;

	@SerializedName("file_type_description")
	private String fileTypeDescription;

	@SerializedName("md5")
	private String md5;
}