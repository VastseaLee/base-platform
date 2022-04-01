package com.base.datamanage.controller;

import com.base.api.common.model.R;
import com.base.api.datamanage.model.BusDataResource;
import com.base.datamanage.dto.input.BusDataResourceSearchInputDTO;
import com.base.datamanage.dto.output.BusDataResourceOutputDTO;
import com.base.datamanage.service.DataResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("dataResource")
@Api(tags = "资源目录")
public class DataResourceController {

    @Autowired
    private DataResourceService dataResourceService;

    @PostMapping("save")
    @ApiOperation("新增")
    public R<Boolean> save(@RequestBody BusDataResource busDataResource, HttpServletRequest request){
        return R.success(dataResourceService.save(busDataResource));
    }

    @DeleteMapping("delById/{id}")
    @ApiOperation("根据id删除")
    public R<Boolean> delById(@PathVariable("id") String id){
        return R.success(dataResourceService.delById(id));
    }

    @PutMapping("update")
    @ApiOperation("更新")
    public R<Boolean> update(@RequestBody BusDataResource busDataResource){
        return R.success(dataResourceService.update(busDataResource));
    }

    @PostMapping("list")
    @ApiOperation("列表查询")
    public R<List<BusDataResourceOutputDTO>> list(BusDataResourceSearchInputDTO dto){
        return R.success(dataResourceService.list(dto));
    }

}
