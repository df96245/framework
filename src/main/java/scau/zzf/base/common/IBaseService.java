package scau.zzf.base.common;

import scau.zzf.base.exception.BaseException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zzf on 2016/11/21.
 */
public interface IBaseService<T> {

    T findByKey(Object key) ;

    int add(T entity);

    int delete(Object key) ;

    int update(T entity) ;

    int updateById(T entity);
    int updateNotNull(T entity);

    /**
     *
     * @param entity setter方法设置更新内容
     * @param valueList 条件值
     * @param propertyName  property=value
     * @return
     */
    int updateSelectProperties(T entity, List<String> valueList, String... propertyName);
    int updateSelectProperties(T entity, String propertyName, Object value);

    List<T> list(Object key) ;

    /**
     * 针对一个类多属性查询，在传入实体类是进行属性赋值
     * 如 user.setUserName("zzf")，对应sql为 WHERE username = ?
     * 支持多属性赋值 对应sql为  username = ? AND password = ? AND role = ?
     * @param entity
     * @return
     */
    List<T> find(T entity);
    T findOne(T entity);

    /**
     * 查询出全部属性,参数为查询条件
     * @param valueList 属性值
     * @param propertyName  属性
     * @return
     */
    List<T> findByProperties(List<String> valueList,String... propertyName) ;

    /**
     *
     * @param propertyList 属性名称
     * @param valueList     属性值 名字对应代码而非数据库 propertyName=value 作为where 条件
     * @param selectProperty 需要查询的属性值 不填则默认查询全部
     * @return
     */
    List<T> findSelectProperty(List<String> propertyList, List valueList, String... selectProperty);
    List<T> findSelectProperty(String[] propertyList, String[] valueList, String... selectProperty);

    List<T> findSelectProperty(String propertyName,Object value,String... selectProperty);
    List<T> findSelectPropertyWithOr(String property, String[] value, String... selectProperty);

//    /**
//     *
//     * @param condition where 的sql 语句
//     * @param selectProperty 需要查询的属性值
//     * @return
//     */
//    List<T> findSelectProperty(String condition, String... selectProperty);
//    List<T> listOrderByProperties(String order,String...propertyName);

    int findCount(T entity) throws Exception;

    /**
     *  全模糊查询 总数 % %
     *
     * @param value 模糊值
     * @param propertyName 属性
     * @return
     */
    int findCountByCondition(String value,String... propertyName) ;
    /**
     * 需要在xml中编写sql语句
     * @param index 第几页  实现层 需要进行-1操作 ，index*pageSize 记录行的偏移量
     * @param pageSize  每页展示的数量
     * @param property 按照什么属性进行排序，在xml中要使用${proerty}
     *                  在实现层 进行limitSize*index操作
     * @return
     */
//
//    List<T> pageEntityOrderByProperty(int index, int pageSize, String property) throws Exception;
//
//    /**
//     *
//     * @param condition 模糊查询条件
//     * @return
//     */
//    List<T> pageEntityBySearchOrderByProperty(int index, int pageSize, String property,String condition)throws Exception;

    /**
     *
     * @param condition 搜索条件
     * @param propertyName 表中字段 多个之间使用or关系
     * @return
     */
     List<T> listEntityByCondition(String condition,String... propertyName);


    /**
     * id数组
     * @param idArray
     */
     void deleteByIds(String[] idArray);

     List<T> findAll();

    /**自己定义xml
     * 批量插入
     * @param list
     */
//    void addBatch(List<T> list)throws Exception;

    /**
     *
     * @param condition
     * @param propertyName
     * @return
     */
    @Deprecated
    List<T> findProperties(String condition, String... propertyName)throws Exception;

}
