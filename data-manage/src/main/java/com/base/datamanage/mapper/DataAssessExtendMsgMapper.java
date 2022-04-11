package com.base.datamanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.api.datamanage.model.DataAssessExtendMsg;

public interface DataAssessExtendMsgMapper extends BaseMapper<DataAssessExtendMsg> {

    /**
     * 修改最新更新时间
     *
     * @param dataAssessExtendMsg
     */
    void replace(DataAssessExtendMsg dataAssessExtendMsg);
}
