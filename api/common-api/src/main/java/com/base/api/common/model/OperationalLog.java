package com.base.api.common.model;

import com.base.api.common.enums.LogEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author dcy
 * @since 2021-01-06
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class OperationalLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 请求地址
     */
    private String url;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 业务模块名称
     */
    private String businessName;

    /**
     * 方法名
     */
    private String method;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 操作状态（0正常 1异常）
     */
    private String logStatus;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 日志类型
     */
    private LogEnum logEnum;

    /**
     * 创建时间
     */
    private Date createDate;
}
