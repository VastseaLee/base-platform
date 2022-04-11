package com.base.datamanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.api.datamanage.model.BusDataSetSql;
import com.base.datamanage.dto.output.BusDataSetSqlOutputDTO;

public interface DataSetSqlMapper extends BaseMapper<BusDataSetSql> {

    /**
     * 根据数据集id查询详情
     *
     * @param dataSetId
     * @return
     */
    BusDataSetSqlOutputDTO selectByDataSetId(String dataSetId);
}
