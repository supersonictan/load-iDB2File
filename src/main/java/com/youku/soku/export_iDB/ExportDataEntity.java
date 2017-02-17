package com.youku.soku.export_iDB;

/**
 * Created by ZeYu
 * Date: 2017/2/14.
 * Time: 19:59.
 * DESC: say something
 */
public class ExportDataEntity {

    public String sql;
    public String appName;
    public String filePath;

    public ExportDataEntity() {
    }

    public ExportDataEntity(String sql, String appName, String filePath) {
        this.sql = sql;
        this.appName = appName;
        this.filePath = filePath;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "ExportDataEntity{" +
                "sql='" + sql + '\'' +
                ", appName='" + appName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
