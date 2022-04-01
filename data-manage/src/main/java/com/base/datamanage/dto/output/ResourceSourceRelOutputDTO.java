package com.base.datamanage.dto.output;

import com.base.api.datamanage.model.BusDataResourceSourceRel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResourceSourceRelOutputDTO extends BusDataResourceSourceRel {

    @ApiModelProperty("表别名")
    private String customTableName;

    @ApiModelProperty("表注释")
    private String remarks;
}
