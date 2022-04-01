package com.base.datamanage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.datamanage.model.BusDataResourceSourceRel;
import com.base.datamanage.dsm.DataSourceRegistry;
import com.base.datamanage.dsm.DefaultDataSource;
import com.base.datamanage.dto.input.ResourceSourceRelSaveInputDTO;
import com.base.datamanage.dto.input.ResourceSourceRelSearchInputDTO;
import com.base.datamanage.dto.output.BusDataTableOutputDTO;
import com.base.datamanage.dto.output.ResourceSourceRelOutputDTO;
import com.base.datamanage.mapper.ResourceSourceRelMapper;
import com.base.datamanage.util.PageUtil;
import com.base.datamanage.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ResourceSourceRelService extends ServiceImpl<ResourceSourceRelMapper,BusDataResourceSourceRel> {

    @Autowired
    private DataSourceRegistry dataSourceRegistry;

    @Autowired
    private ResourceSourceRelMapper resourceSourceRelMapper;

    public Boolean add(BusDataResourceSourceRel rel) {
        rel.setId(SnowFlake.zero.nextId());
        save(rel);
        return true;
    }

    public Boolean delById(String id) {
        resourceSourceRelMapper.deleteById(id);
        return true;
    }

    public Boolean update(BusDataResourceSourceRel rel) {
        resourceSourceRelMapper.updateById(rel);
        return true;
    }

    public PageResult<ResourceSourceRelOutputDTO> page(ResourceSourceRelSearchInputDTO dto, PageModel pageModel) {
        if (pageModel.isPage()) {
            PageHelper.startPage(pageModel.getCurrent().intValue(), pageModel.getSize().intValue());
        }

        List<ResourceSourceRelOutputDTO> list = resourceSourceRelMapper.list(dto);
        if (!CollectionUtils.isEmpty(list)) {
            //加上表的备注信息(实时的,如果以后效率慢了就在bus_data_resource_source_rel表里加个表备注字段改成不实时的)
            Map<String, Map<String, ResourceSourceRelOutputDTO>> map = list.stream().collect(Collectors.groupingBy(ResourceSourceRelOutputDTO::getDataSourceId,
                    Collectors.toMap(ResourceSourceRelOutputDTO::getTableName, Function.identity())));

            for (Map.Entry<String, Map<String, ResourceSourceRelOutputDTO>> entry : map.entrySet()) {
                String dataSourceId = entry.getKey();
                Map<String, ResourceSourceRelOutputDTO> tableMap = entry.getValue();
                //获取对应的数据源
                DefaultDataSource dataSource = dataSourceRegistry.getDataSource(dataSourceId);
                pageModel.setPage(false);
                //查询数据源下的表
                PageResult<BusDataTableOutputDTO> tablePage = dataSource.listTableSource(pageModel);
                List<BusDataTableOutputDTO> tableList = tablePage.getRecords();
                if (!CollectionUtils.isEmpty(tableList)) {
                    for (BusDataTableOutputDTO tableOutputDTO : tableList) {
                        String tableName = tableOutputDTO.getTableName();
                        if (tableMap.containsKey(tableName)) {
                            tableMap.get(tableName).setRemarks(tableOutputDTO.getRemarks());
                        }
                    }
                }
            }
        }
        PageResult<ResourceSourceRelOutputDTO> pageResult = PageUtil.toPage(list);
        return pageResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean batchSave(ResourceSourceRelSaveInputDTO dto) {
        List<BusDataResourceSourceRel> relList = dto.getRelList();
        if (!CollectionUtils.isEmpty(relList)) {
            //首先删除这些表
            resourceSourceRelMapper.removeBySourceTable(relList);
            //新增关联关系
            String dataResourceId = dto.getDataResourceId();
            relList.forEach(bus -> {
                bus.setId(SnowFlake.zero.nextId());
                bus.setDataResourceId(dataResourceId);
            });
            resourceSourceRelMapper.replaceAll(relList);
        }
        return true;
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    public Boolean batchDel(List<BusDataResourceSourceRel> list) {
        resourceSourceRelMapper.removeBySourceTable(list);
        return true;
    }
}
