package com.youku.soku.constant;

import com.taobao.tddl.common.utils.logger.Logger;
import com.taobao.tddl.common.utils.logger.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by ZeYu
 * Date: 2017/2/10.
 * Time: 23:18.
 * DESC: say something
 */
public class SystemConfig {
    private static Logger logger = LoggerFactory.getLogger(SystemConfig.class);

    public static final int LOAD_IDB_TIME_INTERVAL_MIN;

    private static final String APP_NAME_SHOW_KEYWORD = "BAD_CASE_APP";
    private static final String APP_NAME_PERSON_ALIAS = "BAD_CASE_APP";
    private static final String APP_NAME_SHOW_ALIAS = "BAD_CASE_APP";
    private static final String APP_NAME_QUERY_MODIFICATION = "BAD_CASE_APP";
    private static final String APP_NAME_BAD_CASE2 = "BAD_CASE_APP";
    private static final String APP_NAME_RECENCY_WORD = "BAD_CASE_APP";
    private static final String APP_NAME_BLACK_VIDEO = "Q_VIDEO_RANK_APP";

    private static final String SQL_SHOW_KEYWORD = "SELECT * FROM show_keyword";
    private static final String SQL_PERSON_ALIAS = "SELECT * FROM t_person_alias";
    private static final String SQL_QUERY_MODIFICATION = "SELECT * FROM query_modification";
    private static final String SQL_BAD_CASE2 = "SELECT * FROM bad_case2";
    private static final String SQL_RECENCY_WORD = "SELECT * FROM recency_query";
    private static final String SQL_SHOW_ALIAS = "SELECT * FROM t_show_alias";
    private static final String SQL_SHOW_BLACK_VIDEO = "SELECT * FROM video_business_black";

    public static final List<String> EXPORT_FILE_LIST = Arrays.asList(
            "data/show_keyword",
            "data/t_person_alias",
            "data/query_modification",
            "data/bad_case2",
            "data/recency_query",
            "data/t_show_alias",
            "data/video_business_black"
    );

    public static final List<String> APP_NAME_LIST = Arrays.asList(
            APP_NAME_SHOW_KEYWORD,
            APP_NAME_PERSON_ALIAS,
            APP_NAME_SHOW_ALIAS,
            APP_NAME_QUERY_MODIFICATION,
            APP_NAME_BAD_CASE2,
            APP_NAME_RECENCY_WORD,
            APP_NAME_BLACK_VIDEO);

    public static final List<String> SQL_LIST = Arrays.asList(
            SQL_SHOW_KEYWORD,
            SQL_PERSON_ALIAS,
            SQL_SHOW_ALIAS,
            SQL_QUERY_MODIFICATION,
            SQL_BAD_CASE2,
            SQL_RECENCY_WORD,
            SQL_SHOW_BLACK_VIDEO
    );

    public static final String FILE_SUFFIX = "_writing";


    static {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.error("properties is not found", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("load properties failed", e);
            }
        }

        //拉取idb时间周期
        LOAD_IDB_TIME_INTERVAL_MIN = Integer.valueOf(properties.getProperty("load_idb_time_interval_min","10"));

    }



}
