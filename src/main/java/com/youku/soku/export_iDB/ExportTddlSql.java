package com.youku.soku.export_iDB;

import com.taobao.tddl.client.jdbc.TDataSource;
import com.taobao.tddl.common.utils.logger.Logger;
import com.taobao.tddl.common.utils.logger.LoggerFactory;
import com.youku.soku.SystemConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZeYu
 * Date: 2017/2/10.
 * Time: 21:11.
 * DESC: say something
 */
public class ExportTddlSql {
    private static Logger logger = LoggerFactory.getLogger(ExportTddlSql.class);


    public static void searchIDB(String sql, String APP_NAME, String transferName) {
        String newFileName = transferName + SystemConfig.FILE_SUFFIX;
        long st = System.currentTimeMillis();
        TDataSource ds = new TDataSource();
        ds.setAppName(APP_NAME);
        ds.setDynamicRule(true);
        ds.setSharding(false);
        ds.init();

        /*当exception的时候不要覆盖之前生成的正确文件。*/
        boolean needRenameFile = true;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BufferedWriter bw = null;
        int sum = 0;
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            logger.debug("Starting Load " + transferName);
            bw = new BufferedWriter(new FileWriter(new File(newFileName)));
            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                int count = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String val = rs.getString(i);
                    if (i != 1){
                        sb.append("\t");
                    }
                    sb.append(val);
                }
                sb.append("\n");
                bw.write(sb.toString());
                sum ++;
            }
        } catch (SQLException e) {
            needRenameFile = false;
            logger.error("SQL Exception...", e);
        } catch (IOException e) {
            needRenameFile = false;
            logger.error("Write File Exception....", e);
        } catch (Exception e) {
            needRenameFile = false;
            logger.error("Exception", e);
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
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
        if (needRenameFile && (sum > SystemConfig.CHECK_THRESHOLD)) {
            renameFile(transferName);
        }
        logger.error("Finished Load " + newFileName + " Cost:" + (System.currentTimeMillis() - st)/1000 + " s");
    }


    /**每次查询iDB表的线程启动时，先将**/
    private static void renameFile(String transferName) {
        File newFile = new File(transferName + SystemConfig.FILE_SUFFIX);
        //QA要拉取的文件
        File transferFile = new File(transferName);
        /**旧文件不存在直接将新文件改名，否则先删除旧文件再讲新文件改名**/
        if (newFile.exists()) {
            if (transferFile.exists()) {
                transferFile.delete();
            }
            /**renameTo方法在跨文件系统使用时可能会失败，若需要可以使用apache的commons-io代替**/
            newFile.renameTo(transferFile);
        }
    }
}
