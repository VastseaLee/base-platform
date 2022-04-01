package com.base.datamanage.service;

import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.datamanage.dsm.DataSourceRegistry;
import com.base.datamanage.dsm.DefaultDataSource;
import com.base.datamanage.dto.input.DataDelInputDTO;
import com.base.datamanage.dto.input.DataSaveInputDTO;
import com.base.datamanage.dto.input.DataSearchInputDTO;
import com.base.datamanage.dto.input.DataUpdateInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataService {

    @Autowired
    private DataSourceRegistry dataSourceRegistry;

    public PageResult<Map<String,Object>> page(DataSearchInputDTO dto, PageModel pageModel) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        return dataSource.page(dto,pageModel);
    }

    public String save(DataSaveInputDTO dto) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        String result = dataSource.save(dto);
        return result;
    }

    public Boolean del(DataDelInputDTO dto) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        dataSource.del(dto);
        return true;
    }

    public Boolean update(DataUpdateInputDTO dto) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        dataSource.update(dto);
        return true;
    }
}
