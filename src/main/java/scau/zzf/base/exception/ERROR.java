package scau.zzf.base.exception;

/**
 * Created by zzf on 2016/11/24.
 */
public enum ERROR {

    /* 空指针错误 */
    ERROR_NULLPOINTER ,
    /* 参数错误 */
    ERROR_PARAM_ERROR ,
    /* Cache错误 */
    ERROR_CACHE_STORE_ERROR ,
    /* 数据库存储错误 */
    ERROR_STORE_ERROR ,
    /* SQL错误 */
   ERROR_STORE_SQL_ERROR ,
    /* 未知系统异常 */
   ERROR_SYSTEM_ERROR ,
    /* 服务不存在 */
    ERROR_SERVICE_NOT_EXIST ,
    /* service 方法名不支持 */
     ERROR_SERVICE_METHOD_NOT_VALIDATED ;

}
