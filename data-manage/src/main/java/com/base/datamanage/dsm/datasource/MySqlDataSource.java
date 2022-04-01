package com.base.datamanage.dsm.datasource;

import com.base.api.datamanage.model.BusDataSource;
import com.base.datamanage.dsm.DefaultDataSource;


public class MySqlDataSource extends DefaultDataSource {

    public MySqlDataSource(BusDataSource busDataSource) {
        super(busDataSource);
        int start = jdbcUrl.lastIndexOf("/");
        if (start != -1) {
            int end = jdbcUrl.indexOf("?");
            end = end == -1 ? jdbcUrl.length() : end;
            catalog = jdbcUrl.substring(start + 1, end);
        }
        driverClass = "com.mysql.cj.jdbc.Driver";
        addConnectionProperty("useInformationSchema", "true");
    }

    @Override
    protected String getPageSql(String sql, long total, long current, long size) {
        long fromIndex = (current - 1) * size;
        return sql + " LIMIT " + fromIndex + "," + size;
    }

}
