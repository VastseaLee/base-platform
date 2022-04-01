package com.base.datamanage.controller;

import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.common.model.R;
import com.base.datamanage.dto.input.DataDelInputDTO;
import com.base.datamanage.dto.input.DataSaveInputDTO;
import com.base.datamanage.dto.input.DataSearchInputDTO;
import com.base.datamanage.dto.input.DataUpdateInputDTO;
import com.base.datamanage.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("data")
@Api(tags = "数据")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("save")
    @ApiOperation("数据新增")
    public R<String> save(@RequestBody DataSaveInputDTO dto) {
        String msg = dataService.save(dto);
        return msg == null ? R.success(null) : R.error(msg);
    }

    @PostMapping("del")
    @ApiOperation("数据删除")
    public R<Boolean> del(@RequestBody DataDelInputDTO dto) {
        return R.success(dataService.del(dto));
    }

    @PostMapping("update")
    @ApiOperation("数据修改")
    public R<Boolean> update(@RequestBody DataUpdateInputDTO dto) {
        return R.success(dataService.update(dto));
    }

    @PostMapping("page")
    @ApiOperation("数据查询")
    public R<PageResult<Map<String, Object>>> page(DataSearchInputDTO dto, PageModel pageModel) {
        return R.success(dataService.page(dto, pageModel));
    }
}
