package com.base.datamanage.dto.input;

import com.base.api.datamanage.model.BusDataColumn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataColumnSaveInputDTO {

    @ApiModelProperty("数据源id")
    private String dataSourceId;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("别名列表")
    private List<BusDataColumn> list;
}
