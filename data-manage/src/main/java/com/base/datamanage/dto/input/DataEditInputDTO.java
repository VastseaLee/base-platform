package com.base.datamanage.dto.input;

import com.base.datamanage.dto.output.BusDataColumnOutputDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataEditInputDTO {

    @ApiModelProperty("数据源id")
    private String dataSourceId;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("列信息")
    private List<BusDataColumnOutputDTO> colList;

}
