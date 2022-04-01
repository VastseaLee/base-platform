package com.base.datamanage.dto.input;

import com.base.api.datamanage.model.BusDataTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataTableSaveInputDTO {

    @ApiModelProperty("数据源id")
    private String dataSourceId;

    @ApiModelProperty("别名列表")
    private List<BusDataTable> list;
}
