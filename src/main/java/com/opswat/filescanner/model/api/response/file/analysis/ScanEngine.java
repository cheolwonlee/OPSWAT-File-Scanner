package com.opswat.filescanner.model.api.response.file.analysis;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ScanEngine {
    @SerializedName("scan_result_i")
    private String scanResult;
    @SerializedName("scan_time")
    private String scanTime;
    @SerializedName("threat_found")
    private String threatFound;
    @SerializedName("def_time")
    private String defTime;
}
