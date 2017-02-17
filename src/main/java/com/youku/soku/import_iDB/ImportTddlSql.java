package com.youku.soku.import_iDB;

import com.mysql.jdbc.*;
import com.taobao.tddl.client.jdbc.TDataSource;
import com.taobao.tddl.common.utils.logger.Logger;
import com.taobao.tddl.common.utils.logger.LoggerFactory;
import com.youku.soku.SystemConfig;

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZeYu
 * Date: 2017/2/10.
 * Time: 21:11.
 * DESC: say something
 */
public class ImportTddlSql {
    private static Logger logger = LoggerFactory.getLogger(ImportTddlSql.class);


    public static void main(String[] args) {
        importIDB("", "BAD_CASE_APP", "E:\\query_reformulation.bak");
    }

    public static void importIDB(String sql, String APP_NAME, String importFileName) {
        logger.error("Calling Import query_modification to iDB");
        long st = System.currentTimeMillis();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        String d = new SimpleDateFormat("yyyyMMdd").format(date);
        int dayTime = Integer.parseInt(d);
        String insert_sql = "insert into query_modification(day,query,suggestion,score,status,source,method_type,adjusted_score,q_sqv,q_ac,s_sqv,s_ac,cumulative_num,edate,editorID,reason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        TDataSource ds = new TDataSource();
        ds.setAppName(APP_NAME);
        ds.setDynamicRule(true);
        ds.setSharding(false);
        ds.init();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        int count = 0;
        try {
            conn = ds.getConnection();
            /*删除old数据*/
            delOldData(conn);
            /**select**/
            List<String> remainDatas = getManualData(conn);
            logger.debug("!!!!!!!!!!!!"+remainDatas);
            isr = new InputStreamReader(new FileInputStream(importFileName), "gbk");
            br = new BufferedReader(isr);
            conn.setAutoCommit(false);
            //String insert_sql = "insert into query_modification(day,query,suggestion,score,status,source,method_type,adjusted_score,q_sqv,q_ac,s_sqv,s_ac,cumulative_num,edate,editorID,reason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) WHERE query NOT IN " + data;
            ps = conn.prepareStatement(insert_sql);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields.length != 3) {
                    logger.error("Parse Line Exception!:" + line);
                    continue;
                }
                String query = fields[0].trim();
                if (remainDatas.contains(query)){
                    continue;
                }
                String suggestion = fields[1].trim();
                float score = Float.parseFloat(fields[2]);

                ps.setInt(1, dayTime); //day
                ps.setString(2, query); //query
                ps.setString(3, suggestion);    //suggestion
                ps.setFloat(4, score);  //score
                ps.setString(5, "2");   //status
                ps.setString(6, "2");   //source
                ps.setString(7, "0");   //method_type
                ps.setFloat(8, 0F);   //adjusted_score
                ps.setInt(9, 0);    //q_sqv
                ps.setFloat(10, 0F);    // q_ac
                ps.setInt(11, 0);   // s_sqv
                ps.setFloat(12, 0F);    // s_ac
                ps.setInt(13, 0);   // cumulative_num
                ps.setTimestamp(14, timestamp);// edate
                ps.setInt(15, 39);  // editorID
                ps.setInt(16, 1);// reason
                //ps.executeUpdate();
                ps.addBatch();
                count++;
                if (count %20000 == 0) {
                    ps.executeBatch();
                    conn.commit();
                }
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            logger.error("SQL Exception...", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
                if (rs !=null){
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.error("Finished import " + importFileName + " to iDB count:" + count + " Cost:" + (System.currentTimeMillis()-st)/1000+"s");
    }

    public static void delOldData(Connection conn) {
        String sql = "delete from query_modification where editorID!=1";
        long st = System.currentTimeMillis();
        //String sql = "delete from query_modification";
        PreparedStatement psts = null;
        int count = 0;
        try {
            psts = conn.prepareStatement(sql);
            count = psts.executeUpdate();
        } catch (SQLException e) {
            logger.error("Delete Exception!",e);
        } finally {
            if (psts != null) {
                try {
                    psts.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.debug("Delete count:" + count + ",cost:" + (System.currentTimeMillis()-st)/1000 + "s");

    }
    public static List<String> getManualData(Connection conn) {
        String sql = "select query from query_modification";
        PreparedStatement psts = null;
        ResultSet rs = null;
        int count = 0;
        List<String> list = new ArrayList<String>();
        try {
            psts = conn.prepareStatement(sql);
            rs = psts.executeQuery();

            while (rs.next()) {
                int cols = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= cols; i++) {
                    String val = rs.getString(i);

                    list.add(val);
                }
            }
        } catch (SQLException e) {
            logger.error("Delete Exception!",e);
        } finally {
            try {
                if (psts != null) {
                    psts.close();
                }
                if (rs !=null) {
                    rs.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
