package com.base.datamanage.dto.output;

import com.base.api.datamanage.model.BusDataSetResField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BusDataSetPreviewOutputDTO {

    @ApiModelProperty("返回字段列表")
    private List<BusDataSetResField> fieldList;

    @ApiModelProperty("返回值")
    private Object res;
}
