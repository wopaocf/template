package com.imooc.passbook.constant;

/**
 * 常量定义
 */
public class Constants {
    /**商户优惠券 kafaka Topic */
    public static  final String TEMPLATE_TOPIC="merchants-template";
    /** token  文件存储目录*/
    public static  final String TOKEN_DIR="/tmp/token/";
     /** 已使用的 token  文件后缀*/
    public static  final String  USED_TOKEN_SUFFIX="_";
     /**  用户数的redis key*/
    public static  final String  USE_COUNT_REDIS_KEY="imooc-user-count";

    /**
     * User HBASE Table
     */
    public class  UserTable{
        /** User HBase 表名*/
        public static  final String TABLE_NAME="pb:user";
        /**基本信息列族*/
        public  static final String FAMILY_B="b";
        /**用户名*/
        public  static final String NAME="name";
        /**用户年龄*/
        public  static final String AGE="age";
        /**用户性别*/
        public  static final String SEX="sex";

        /** 额外信息列族*/
        public static final String FAMILY_O="O";
        /** 电话号码**/
        public static final String PHONE="phone";
        /** 住址**/
        public static final String ADDRESS="address";
    }

    /**
     * PassTemplate HBase Table
     */
    public  class PassTemplateTable{
        /**
         * PassTemplate HBase 表名**/
        public static final String TABLE_NAME="pb:template";
        /** 基本信息列族**/
        public static final String FAMILY_B="b";
        /** 商户 id**/
        public static final String ID="id";
        /** 优惠券 标题**/
        public static final String TITLE="title";
        /** 优惠券 摘要**/
        public static final String SUMMARY="summary";
        /** 优惠券 详细信息*/
        public static final String DESC="desc";
        /** 标识优惠券 是否有token*/
        public static final String HAS_TOKEN="has_token";
        /** 优惠券背景色*/
        public static final String BACKGROUD="backgroud";



        /** 约束信息列族**/
        public static final String FAMILY_C="c";
        /** 最大个数限制*/
        public static final String LIMIT="limit";
        /** 优惠券开始时间*/
        public static final String STRAT="start";
        /** 优惠券结束时间*/
        public static final String END="end";
    }

    /**
     * Pass HBase Table
     */
    public class PassTable{
     /** Pass HBase 表名 */
     public static final String TABLE_NAME="pb:pass";
      /**信息列族*/
     public static final String FAMILY_I="i";
     /**用户 id*/
     public static final String USER_ID="user_id";
     /**优惠券 id*/
     public static final String TEMPLATE_ID="template_id";
     /**优惠券识别码*/
     public static final String TOKEN="token";
     /**领取日期*/
     public static final String ASSIGNED_DATE="assigned_date";
     /**消费日期*/
     public static final String CON_DATE="con_date";
    }

    /**
     * Feedback HBase Table
     */
    public  class Feedback{
    /**Feedback HBase 表名*/
    public static final String TABLE_NAME="pb:feedback";
    /**Feedback HBase 表名*/
    public static final String FAMILY_I="i";
    /**用户 id*/
    public static final String USER_ID="user_id";
    /**评论类型*/
    public static final String TYPE="type";
    /**PassTemplate RowKey 如果是app评论，则是-1*/
    public static final String TEMPLATE_ID="template_id";
    /**评论内容*/
    public static final String COMMENT="comment";


    }


}
