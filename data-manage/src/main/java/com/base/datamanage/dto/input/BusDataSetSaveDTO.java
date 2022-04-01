package com.base.datamanage.dto.input;

import com.alibaba.fastjson.JSONObject;
import com.base.api.datamanage.model.BusDataSet;
import com.base.api.datamanage.model.BusDataSetParam;
import com.base.api.datamanage.model.BusDataSetResField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class BusDataSetSaveDTO extends BusDataSet {

    @ApiModelProperty("数据集详情")
    private JSONObject setDetail;

    @ApiModelProperty("数据集参数")
    private List<BusDataSetParam> paramList;

    @ApiModelProperty("返回字段列表")
    private List<BusDataSetResField> fieldList;

}
