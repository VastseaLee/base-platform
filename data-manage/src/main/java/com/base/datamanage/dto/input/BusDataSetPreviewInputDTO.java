package com.base.datamanage.dto.input;

import com.alibaba.fastjson.JSONObject;
import com.base.api.datamanage.model.BusDataSet;
import com.base.api.datamanage.model.BusDataSetParam;
import com.base.api.datamanage.model.BusDataSetResField;
import com.base.datamanage.dto.output.BusDataSetSelectOutputDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class BusDataSetPreviewInputDTO extends BusDataSet {

    @ApiModelProperty("数据集详情")
    private JSONObject setDetail;

    @ApiModelProperty("数据集参数")
    private List<BusDataSetParam> paramList;

    @ApiModelProperty("返回字段列表")
    private List<BusDataSetResField> fieldList;

    @ApiModelProperty("字段是否需要更新")
    private boolean fieldUpdate;

    @ApiModelProperty("入参")
    private Map<String,Object> param;

    public BusDataSetPreviewInputDTO(){}

    public BusDataSetPreviewInputDTO(BusDataSetSelectOutputDTO detail){
        this.setDetail = JSONObject.parseObject(JSONObject.toJSONString(detail.getSetDetail()));
        this.paramList = detail.getParamList();
        this.fieldList = detail.getFieldList();
        setDataSetType(detail.getDataSetType());

    }
}
