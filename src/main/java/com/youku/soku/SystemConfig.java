package com.youku.soku;

import com.taobao.tddl.common.utils.logger.Logger;
import com.taobao.tddl.common.utils.logger.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by ZeYu
 * Date: 2017/2/14.
 * Time: 20:01.
 * DESC: say something
 */
public class SystemConfig {

    private static Logger logger = LoggerFactory.getLogger(SystemConfig.class);
    public static final int LOAD_IDB_TIME_INTERVAL_MIN;     //定时加载 iDB 的时间周期。eg：2min
    public static final String FILE_SUFFIX;     //文件后缀。因load iDB数据会耗时，为了确保QA读取的文件都是load finished文件，加载中的文件命名为:show_keyword_writing,正确加载后重命名为show_keyword,qa读取后面的文件。
    public static final List<DataEntity> entityList = new ArrayList<DataEntity>();


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
        FILE_SUFFIX = properties.getProperty("FILE_SUFFIX");

        //节目关键词
        String SHOW_KEYWORD_APP_NAME = properties.getProperty("SHOW_KEYWORD_APP_NAME");
        String SHOW_KEYWORD_SQL = properties.getProperty("SHOW_KEYWORD_SQL");
        String SHOW_KEYWORD_FILEPATH = properties.getProperty("SHOW_KEYWORD_FILEPATH");
        entityList.add(new DataEntity(SHOW_KEYWORD_SQL, SHOW_KEYWORD_APP_NAME, SHOW_KEYWORD_FILEPATH));

        //人物别名
        String PERSON_ALIAS_APP_NAME = properties.getProperty("PERSON_ALIAS_APP_NAME");
        String PERSON_ALIAS_SQL = properties.getProperty("PERSON_ALIAS_SQL");
        String PERSON_ALIAS_FILEPATH = properties.getProperty("PERSON_ALIAS_FILEPATH");
        entityList.add(new DataEntity(PERSON_ALIAS_SQL, PERSON_ALIAS_APP_NAME, PERSON_ALIAS_FILEPATH));

        //节目别名
        String SHOW_ALIAS_APP_NAME = properties.getProperty("SHOW_ALIAS_APP_NAME");
        String SHOW_ALIAS_SQL = properties.getProperty("SHOW_ALIAS_SQL");
        String SHOW_ALIAS_FILEPATH = properties.getProperty("SHOW_ALIAS_FILEPATH");
        entityList.add(new DataEntity(SHOW_ALIAS_SQL, SHOW_ALIAS_APP_NAME, SHOW_ALIAS_FILEPATH));

        //查询修改
        String QUERY_MODIFICATION_APP_NAME = properties.getProperty("QUERY_MODIFICATION_APP_NAME");
        String QUERY_MODIFICATION_SQL = properties.getProperty("QUERY_MODIFICATION_SQL");
        String QUERY_MODIFICATION_FILEPATH = properties.getProperty("QUERY_MODIFICATION_FILEPATH");
        entityList.add(new DataEntity(QUERY_MODIFICATION_SQL, QUERY_MODIFICATION_APP_NAME, QUERY_MODIFICATION_FILEPATH));

        //拼写纠错
        String BAD_CASE2_APP_NAME = properties.getProperty("BAD_CASE2_APP_NAME");
        String BAD_CASE2_SQL = properties.getProperty("BAD_CASE2_SQL");
        String BAD_CASE2_FILEPATH = properties.getProperty("BAD_CASE2_FILEPATH");
        entityList.add(new DataEntity(BAD_CASE2_SQL, BAD_CASE2_APP_NAME, BAD_CASE2_FILEPATH));

        //搜索时效性词
        String RECENCY_WORD_APP_NAME = properties.getProperty("RECENCY_WORD_APP_NAME");
        String RECENCY_WORD_SQL = properties.getProperty("RECENCY_WORD_SQL");
        String RECENCY_WORD_FILEPATH = properties.getProperty("RECENCY_WORD_FILEPATH");
        entityList.add(new DataEntity(RECENCY_WORD_SQL, RECENCY_WORD_APP_NAME, RECENCY_WORD_FILEPATH));

        //视频黑库
        String BLACK_VIDEO_APP_NAME = properties.getProperty("BLACK_VIDEO_APP_NAME");
        String BLACK_VIDEO_SQL = properties.getProperty("BLACK_VIDEO_SQL");
        String BLACK_VIDEO_FILEPATH = properties.getProperty("BLACK_VIDEO_FILEPATH");
        entityList.add(new DataEntity(BLACK_VIDEO_SQL, BLACK_VIDEO_APP_NAME, BLACK_VIDEO_FILEPATH));

        //好词词典
        String HEYI_DICTIONARY_APP_NAME = properties.getProperty("HEYI_DICTIONARY_APP_NAME");
        String HEYI_DICTIONARY_SQL = properties.getProperty("HEYI_DICTIONARY_SQL");
        String HEYI_DICTIONARY_FILENAME = properties.getProperty("HEYI_DICTIONARY_FILENAME");
        entityList.add(new DataEntity(HEYI_DICTIONARY_SQL, HEYI_DICTIONARY_APP_NAME, HEYI_DICTIONARY_FILENAME));

        logger.debug("Load dataList finished!" + entityList);

    }



}
