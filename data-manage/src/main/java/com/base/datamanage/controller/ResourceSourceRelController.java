package com.base.datamanage.controller;


import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.common.model.R;
import com.base.api.datamanage.model.BusDataResourceSourceRel;
import com.base.datamanage.dto.input.ResourceSourceRelSaveInputDTO;
import com.base.datamanage.dto.input.ResourceSourceRelSearchInputDTO;
import com.base.datamanage.dto.output.ResourceSourceRelOutputDTO;
import com.base.datamanage.service.ResourceSourceRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("resourceSourceRel")
@Api(tags = "表格资源关系")
public class ResourceSourceRelController {

    @Autowired
    private ResourceSourceRelService resourceSourceRelService;

    @PostMapping("save")
    @ApiOperation("新增")
    public R<Boolean> save(@RequestBody BusDataResourceSourceRel rel){
        return R.success(resourceSourceRelService.add(rel));
    }

    @PostMapping("batchSave")
    @ApiOperation("批量新增")
    public R<Boolean> batchSave(@RequestBody ResourceSourceRelSaveInputDTO dto){
        return R.success(resourceSourceRelService.batchSave(dto));
    }

    @DeleteMapping("delById/{id}")
    @ApiOperation("根据id删除")
    public R<Boolean> delById(@PathVariable("id") String id){
        return R.success(resourceSourceRelService.delById(id));
    }

    @PostMapping("batchDel")
    @ApiOperation("批量删除")
    public R<Boolean> batchDel(@RequestBody List<BusDataResourceSourceRel> list){
        return R.success(resourceSourceRelService.batchDel(list));
    }

    @PutMapping("update")
    @ApiOperation("更新")
    public R<Boolean> update(@RequestBody BusDataResourceSourceRel rel){
        return R.success(resourceSourceRelService.update(rel));
    }

    @PostMapping("page")
    @ApiOperation("分页查询")
    public R<PageResult<ResourceSourceRelOutputDTO>> page(ResourceSourceRelSearchInputDTO dto, PageModel pageModel){
        return R.success(resourceSourceRelService.page(dto,pageModel));
    }

}
