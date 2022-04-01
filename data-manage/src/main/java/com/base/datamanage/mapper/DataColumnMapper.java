package com.base.datamanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.api.datamanage.model.BusDataColumn;
import com.base.datamanage.dto.input.BusDataColumnSearchInputDTO;

import java.util.List;


public interface DataColumnMapper extends BaseMapper<BusDataColumn> {

    /**
     * 列别名查询
     *
     * @param dto
     * @return
     */
    List<BusDataColumn> listColumnExtend(BusDataColumnSearchInputDTO dto);
}
