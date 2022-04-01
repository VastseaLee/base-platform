package com.base.datamanage.service;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.datamanage.model.BusDataSource;
import com.base.datamanage.dsm.DefaultDataSource;
import com.base.datamanage.dto.input.BusDataSourceSearchInputDTO;
import com.base.datamanage.dto.output.BusDataSourceOutputDTO;
import com.base.datamanage.mapper.DataSourceMapper;
import com.base.datamanage.util.PageUtil;
import com.base.datamanage.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    public BusDataSource queryById(String id){
        return dataSourceMapper.selectById(id);
    }

    public Boolean save(BusDataSource busDataSource) {
        busDataSource.setId(SnowFlake.zero.nextId());
        dataSourceMapper.insert(busDataSource);
        return true;
    }

    public Boolean delById(String id) {
        dataSourceMapper.deleteById(id);
        return true;
    }

    public PageResult<BusDataSourceOutputDTO> page(BusDataSourceSearchInputDTO dto, PageModel pageModel) {
        if(pageModel.isPage()){
            PageHelper.startPage(pageModel.getCurrent().intValue(),pageModel.getSize().intValue());
        }
        return PageUtil.toPage(dataSourceMapper.list(dto));
    }

    public Boolean update(BusDataSource busDataSource) {
        dataSourceMapper.updateById(busDataSource);
        return true;
    }

    public Boolean testConnection(BusDataSource busDataSource) {
        DefaultDataSource dataSource = new DefaultDataSource(busDataSource);
        DruidPooledConnection connection = null;
        try {
            connection = dataSource.getConnection();
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection != null;
    }
}
