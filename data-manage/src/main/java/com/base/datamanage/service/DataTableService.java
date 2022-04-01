package com.base.datamanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.datamanage.model.BusDataTable;
import com.base.datamanage.dsm.DataSourceRegistry;
import com.base.datamanage.dsm.DefaultDataSource;
import com.base.datamanage.dto.input.BusDataTableSearchInputDTO;
import com.base.datamanage.dto.input.DataTableSaveInputDTO;
import com.base.datamanage.dto.output.BusDataTableOutputDTO;
import com.base.datamanage.mapper.DataTableMapper;
import com.base.datamanage.mapper.ResourceSourceRelMapper;
import com.base.datamanage.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataTableService extends ServiceImpl<DataTableMapper, BusDataTable> {

    @Autowired
    private DataSourceRegistry dataSourceRegistry;

    @Autowired
    private ResourceSourceRelMapper resourceSourceRelMapper;

    @Autowired
    private DataTableMapper dataTableMapper;

    public PageResult<BusDataTableOutputDTO> page(BusDataTableSearchInputDTO dto, PageModel pageModel) {
        String dataSourceId = dto.getDataSourceId();
        DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dataSourceId);

        //原始表
        PageResult<BusDataTableOutputDTO> sourcePage = dataSource.listTableSource(pageModel);
        List<BusDataTableOutputDTO> sourceList = sourcePage.getRecords();
        if (!CollectionUtils.isEmpty(sourceList)) {
            //表的扩展信息
            List<String> tableNameList = sourceList.stream().map(BusDataTableOutputDTO::getTableName).collect(Collectors.toList());
            //合并表的扩展信息
            List<BusDataTable> extendList = dataTableMapper.listTableExtend(tableNameList, dataSourceId);
            if (!CollectionUtils.isEmpty(extendList)) {
                Map<String, BusDataTable> extendMap = extendList.stream()
                        .collect(Collectors.toMap(b -> getTableUniqueKey(b), Function.identity()));
                //遍历加上扩展名
                sourceList.forEach(source -> {
                    String key = getTableUniqueKey(source);
                    if (extendMap.containsKey(key)) {
                        BusDataTable busDataTable = extendMap.get(key);
                        source.setId(busDataTable.getId());
                        source.setCustomTableName(busDataTable.getCustomTableName());
                    }
                });
            }

            List<BusDataTableOutputDTO> relList = resourceSourceRelMapper.list2(dataSourceId, tableNameList);
            if (!CollectionUtils.isEmpty(relList)) {
                Map<String, BusDataTableOutputDTO> relMap = relList.stream()
                        .collect(Collectors.toMap(b -> getTableUniqueKey(b), Function.identity()));
                //遍历加上资源目录
                sourceList.forEach(source -> {
                    String key = getTableUniqueKey(source);
                    if (relMap.containsKey(key)) {
                        BusDataTableOutputDTO rel = relMap.get(key);
                        source.setDataResourceId(rel.getDataResourceId());
                        source.setResourceName(rel.getResourceName());
                    }
                });
            }
        }

        return sourcePage;
    }

    private String getTableUniqueKey(BusDataTable busDataTable) {
        return busDataTable.getTableName();
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean batchSave(DataTableSaveInputDTO dto) {
        String dataSourceId = dto.getDataSourceId();
        //首先删除旧的
        LambdaQueryWrapper<BusDataTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusDataTable::getDataSourceId, dataSourceId);
        remove(queryWrapper);

        //批量保存新的
        List<BusDataTable> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(busDataColumn -> {
                busDataColumn.setId(SnowFlake.zero.nextId());
                busDataColumn.setDataSourceId(dataSourceId);
            });
            saveBatch(list);
        }

        return true;
    }
}
