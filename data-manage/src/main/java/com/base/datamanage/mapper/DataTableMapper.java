package com.base.datamanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.api.datamanage.model.BusDataTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataTableMapper extends BaseMapper<BusDataTable> {

    /**
     * 表别名查询
     *
     * @param tableNameList
     * @param dataSourceId
     * @return
     */
    List<BusDataTable> listTableExtend(@Param("tableNameList") List<String> tableNameList, @Param("dataSourceId") String dataSourceId);
}
