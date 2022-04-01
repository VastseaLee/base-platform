package com.base.datamanage.dsm.datasource;

import com.base.api.datamanage.model.BusDataSource;
import com.base.datamanage.dsm.DefaultDataSource;

public class SqlServerDataSource extends DefaultDataSource {

    public SqlServerDataSource(BusDataSource busDataSource) {
        super(busDataSource);
        schema = "dbo";
    }

    /**
     * 子类务必重写！！！！！
     *
     * @param sql
     * @param total
     * @param current
     * @param size
     * @return
     */
    @Override
    public String getPageSql(String sql, long total, long current, long size) {
        long fromIndex = (current - 1) * size;
        return "SELECT page_temp.*,0 AS _NAV_ORDER_F_ FROM (" + sql +
                " ) page_temp ORDER BY _NAV_ORDER_F_ OFFSET " +
                fromIndex + " ROWS FETCH NEXT " + size + " ROWS ONLY";
    }


}
