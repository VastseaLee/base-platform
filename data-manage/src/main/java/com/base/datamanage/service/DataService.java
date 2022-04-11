package com.base.datamanage.service;

import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.datamanage.model.DataAssessExtendMsg;
import com.base.datamanage.dsm.DataSourceRegistry;
import com.base.datamanage.dsm.DefaultDataSource;
import com.base.datamanage.dto.input.DataDelInputDTO;
import com.base.datamanage.dto.input.DataSaveInputDTO;
import com.base.datamanage.dto.input.DataSearchInputDTO;
import com.base.datamanage.dto.input.DataUpdateInputDTO;
import com.base.datamanage.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    private DataSourceRegistry dataSourceRegistry;

    @Autowired
    private DataAssessExtendMsgService dataAssessExtendMsgService;

    public PageResult<Map<String, Object>> page(DataSearchInputDTO dto, PageModel pageModel) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        return dataSource.page(dto, pageModel);
    }

    public String save(DataSaveInputDTO dto) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        String result = dataSource.save(dto);
        saveUpdate(dto.getDataSourceId(), dto.getTableName());
        return result;
    }

    public Boolean del(DataDelInputDTO dto) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        dataSource.del(dto);
        saveUpdate(dto.getDataSourceId(), dto.getTableName());
        return true;
    }

    public Boolean update(DataUpdateInputDTO dto) {
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dto.getDataSourceId());
        dataSource.update(dto);
        saveUpdate(dto.getDataSourceId(), dto.getTableName());
        return true;
    }

    private void saveUpdate(String dataSourceId, String tableName) {
        DataAssessExtendMsg dataAssessExtendMsg = new DataAssessExtendMsg();
        dataAssessExtendMsg.setDataSourceId(dataSourceId);
        dataAssessExtendMsg.setTableName(tableName);
        dataAssessExtendMsg.setId(SnowFlake.zero.nextId());
        dataAssessExtendMsg.setUpdateTime(new Date());
        dataAssessExtendMsgService.replace(dataAssessExtendMsg);
    }
}
