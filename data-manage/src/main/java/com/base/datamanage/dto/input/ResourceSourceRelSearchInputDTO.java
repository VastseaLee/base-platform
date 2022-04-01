package com.base.datamanage.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResourceSourceRelSearchInputDTO {

    @ApiModelProperty("资源目录id")
    private String dataResourceId;

    @ApiModelProperty("数据源id")
    private String dataSourceId;

    @ApiModelProperty("表列表")
    private List<String> tableNameList;
}
