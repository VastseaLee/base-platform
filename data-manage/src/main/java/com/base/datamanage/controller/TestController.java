package com.base.datamanage.controller;


import com.base.api.common.model.R;
import com.base.api.datamanage.model.BusDataSetParam;
import com.base.datamanage.dto.input.TestInputDTO;
import io.swagger.annotations.Api;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
@Api(tags = "转发测试")
public class TestController {

    @GetMapping("testGetParam")
    public R<String> testGetParam(String id){
        return R.success(id);
    }

    @GetMapping("testGetPath/{id}")
    public R<String> testGetPath(@PathVariable("id") String id){
        return R.success(id);
    }

    @PostMapping("testPostJson")
    public R<TestInputDTO> testPostJson(@RequestBody TestInputDTO dto){
        return R.success(dto);
    }

    @PostMapping("testPostJson2")
    public R<List<TestInputDTO>> testPostJson2(@RequestBody TestInputDTO dto){
        List<TestInputDTO> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(dto);
        }
        return R.success(list);
    }

    @PostMapping("testPostJson3")
    public R<List<String>> testPostJson3(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i+"");
        }
        return R.success(list);
    }

    @PostMapping("testPostForm")
    public R<TestInputDTO> testPostForm(TestInputDTO dto){
        if(CollectionUtils.isEmpty(dto.getParamList())){
            List<BusDataSetParam> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                BusDataSetParam busDataSetParam = new BusDataSetParam();
                busDataSetParam.setId(""+i);
                busDataSetParam.setParamName("name"+i);
                list.add(busDataSetParam);
            }
            dto.setParamList(list);
        }
        return R.success(dto);
    }
}
