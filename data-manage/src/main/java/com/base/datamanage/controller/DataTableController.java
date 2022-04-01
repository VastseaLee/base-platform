package com.base.datamanage.controller;


import com.base.api.common.model.PageModel;
import com.base.api.common.model.PageResult;
import com.base.api.common.model.R;
import com.base.datamanage.dto.input.BusDataTableSearchInputDTO;
import com.base.datamanage.dto.input.DataTableSaveInputDTO;
import com.base.datamanage.dto.output.BusDataTableOutputDTO;
import com.base.datamanage.service.DataTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dataTable")
@Api(tags = "数据表")
public class DataTableController {

    @Autowired
    private DataTableService dataTableService;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public R<PageResult<BusDataTableOutputDTO>> page(BusDataTableSearchInputDTO dto, PageModel pageModel) {
        return R.success(dataTableService.page(dto,pageModel));
    }

    @PostMapping("batchSave")
    @ApiOperation("批量保存")
    public R<Boolean> batchSave(@RequestBody DataTableSaveInputDTO dto) {
        return R.success(dataTableService.batchSave(dto));
    }
}
