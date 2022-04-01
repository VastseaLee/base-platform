package com.base.datamanage.dto.input;

import com.base.api.datamanage.model.BusDataTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BusDataTableSearchInputDTO extends BusDataTable {

    @ApiModelProperty("注释信息")
    private String remarks;

}
