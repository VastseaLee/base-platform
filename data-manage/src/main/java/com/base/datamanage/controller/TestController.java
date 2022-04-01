package com.base.datamanage.controller;


import com.base.api.common.model.R;
import com.base.datamanage.dto.input.TestInputDTO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("testPostForm")
    public R<TestInputDTO> testPostForm(TestInputDTO dto){
        return R.success(dto);
    }
}
