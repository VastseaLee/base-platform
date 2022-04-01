package com.base.datamanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.api.datamanage.model.BusDataSource;
import com.base.datamanage.dto.input.BusDataSourceSearchInputDTO;
import com.base.datamanage.dto.output.BusDataSourceOutputDTO;

import java.util.List;

public interface DataSourceMapper extends BaseMapper<BusDataSource> {

    /**
     * 数据源列表查询
     *
     * @param dto
     * @return
     */
    List<BusDataSourceOutputDTO> list(BusDataSourceSearchInputDTO dto);
}
