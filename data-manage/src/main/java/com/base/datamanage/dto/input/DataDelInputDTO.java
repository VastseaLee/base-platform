package com.base.datamanage.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class DataDelInputDTO extends DataEditInputDTO {

    @ApiModelProperty("数据")
    private Map<String,Object> data;

}
