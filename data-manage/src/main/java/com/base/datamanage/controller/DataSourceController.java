package com.base.datamanage.controller;

import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.common.model.R;
import com.base.api.datamanage.model.BusDataSource;
import com.base.datamanage.dto.input.BusDataSourceSearchInputDTO;
import com.base.datamanage.dto.output.BusDataSourceOutputDTO;
import com.base.datamanage.service.DataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("dataSource")
@Api(tags = "数据源")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @PostMapping("save")
    @ApiOperation("新增")
    public R<Boolean> save(@RequestBody BusDataSource busDataSource, HttpServletRequest request){
        return R.success(dataSourceService.save(busDataSource));
    }

    @DeleteMapping("delById/{id}")
    @ApiOperation("根据id删除")
    public R<Boolean> delById(@PathVariable("id") String id){
        return R.success(dataSourceService.delById(id));
    }

    @PutMapping("update")
    @ApiOperation("更新")
    public R<Boolean> update(@RequestBody BusDataSource busDataSource){
        return R.success(dataSourceService.update(busDataSource));
    }

    @PostMapping("page")
    @ApiOperation("分页查询")
    public R<PageResult<BusDataSourceOutputDTO>> page(BusDataSourceSearchInputDTO dto, PageModel pageModel){
        return R.success(dataSourceService.page(dto,pageModel));
    }

    @PostMapping("testConnection")
    @ApiOperation("测试连接")
    public R<Boolean> testConnection(@RequestBody BusDataSource busDataSource){
        return R.success(dataSourceService.testConnection(busDataSource));
    }

}
