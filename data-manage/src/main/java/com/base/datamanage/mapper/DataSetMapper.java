package com.base.datamanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.api.datamanage.model.BusDataSet;
import com.base.datamanage.dto.input.BusDataSetSearchInputDTO;
import com.base.datamanage.dto.output.BusDataSetOutputDTO;

import java.util.List;

public interface DataSetMapper extends BaseMapper<BusDataSet> {

    /**
     * 数据集列表查询
     *
     * @param dto
     * @return
     */
    List<BusDataSetOutputDTO> list(BusDataSetSearchInputDTO dto);
}
