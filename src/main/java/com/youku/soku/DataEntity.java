package com.youku.soku;

/**
 * Created by ZeYu
 * Date: 2017/2/14.
 * Time: 19:59.
 * DESC: say something
 */
public class DataEntity {

    public String sql;
    public String appName;
    public String filePath;

    public DataEntity() {
    }

    public DataEntity(String sql, String appName, String filePath) {
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
        return "DataEntity{" +
                "sql='" + sql + '\'' +
                ", appName='" + appName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
