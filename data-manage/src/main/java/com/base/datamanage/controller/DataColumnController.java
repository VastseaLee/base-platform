package com.base.datamanage.controller;

import com.base.api.common.model.R;
import com.base.datamanage.dto.input.BusDataColumnSearchInputDTO;
import com.base.datamanage.dto.input.DataColumnSaveInputDTO;
import com.base.datamanage.dto.output.BusDataColumnOutputDTO;
import com.base.datamanage.service.DataColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dataColumn")
@Api(tags = "数据列")
public class DataColumnController {

    @Autowired
    private DataColumnService dataColumnService;

    @PostMapping("list")
    @ApiOperation("列表查询")
    public R<List<BusDataColumnOutputDTO>> list(@RequestBody BusDataColumnSearchInputDTO dto) {
        return R.success(dataColumnService.list(dto));
    }

    @PostMapping("batchSave")
    @ApiOperation("批量新增列别名")
    public R<Boolean> batchSave(@RequestBody DataColumnSaveInputDTO dto) {
        return R.success(dataColumnService.batchSave(dto));
    }
}
