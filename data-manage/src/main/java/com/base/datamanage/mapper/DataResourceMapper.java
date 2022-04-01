package com.base.datamanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.api.datamanage.model.BusDataResource;
import com.base.datamanage.dto.input.BusDataResourceSearchInputDTO;
import com.base.datamanage.dto.output.BusDataResourceOutputDTO;

import java.util.List;

public interface DataResourceMapper extends BaseMapper<BusDataResource> {

    /**
     * 资源目录查询
     *
     * @param dto
     * @return
     */
    List<BusDataResourceOutputDTO> list(BusDataResourceSearchInputDTO dto);
}
