package com.base.datamanage.dto.output;

import com.base.api.datamanage.model.BusDataSetSql;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BusDataSetSqlOutputDTO extends BusDataSetSql {

    @ApiModelProperty("数据源名称")
    private String dataBaseName;
}
