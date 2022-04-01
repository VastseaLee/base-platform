package com.base.api.common.model;

import lombok.Data;

/**
 * @Author：dcy
 * @Description: 业务日志对象
 * @Date: 2020/10/20 8:37
 */
@Data
public class EsModel<T> {

    private String id;
    private T data;

    public EsModel() {

    }

    public EsModel(String id, T data) {
        this.id = id;
        this.data = data;
    }
}
