package scau.zzf.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import scau.zzf.base.common.IBaseMapper;
import scau.zzf.base.common.IBaseService;
import scau.zzf.base.util.Reflections;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zzf on 2016/11/21.
 */
public abstract class AbstractBaseService<T> implements IBaseService<T> {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected IBaseMapper<T> baseMapper;

    private Class<?> mapperClass;

    public AbstractBaseService() {
        mapperClass = Reflections.getClassGenricType(this.getClass());

    }


    public T findByKey(Object key)  {
        return baseMapper.selectByPrimaryKey(key);
    }


    public int add(T entity) {
        return baseMapper.insert(entity);
    }

    public int delete(Object key) {
        return baseMapper.deleteByPrimaryKey(key);
    }

    public int update(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

    public int updateNotNull(T entity)  {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    public int updateById(T entity)  {
        return baseMapper.updateByPrimaryKey(entity);
    }


    public int updateSelectProperties(T entity, List<String> valueList, String... propertyName) {
        Example example = new Example(mapperClass);
        //使用条件进行更新
        Example.Criteria criteria = example.createCriteria();

        if (propertyName != null && propertyName.length >= 1) {
            if (valueList.isEmpty()) {
                throw new RuntimeException("valueList 不能为空");
            }

            for (int e = 0; e < propertyName.length; ++e) {
                //写入条件,如username=zzf
                criteria.andCondition(propertyName[e] + "=", valueList.get(e));
            }
        } else {
            throw new RuntimeException("propertyName不能为空!");
        }

        return baseMapper.updateByExampleSelective(entity, example);
    }

    public int updateSelectProperties(T entity, String propertyName, Object value)  {
        Example example = new Example(mapperClass);
        //使用条件进行更新
        Example.Criteria criteria = example.createCriteria();

        if (propertyName != null) {
            if (value == null) {
                throw new RuntimeException("value 不能为空");
            }
            //写入条件,如username=zzf
            criteria.andEqualTo(propertyName, value);
        } else {
            throw new RuntimeException("propertyName不能为空!");
        }

        return baseMapper.updateByExampleSelective(entity, example);
    }

    public List<T> find(T entity){
        return baseMapper.select(entity);
    }

    public T findOne(T entity) {
        return baseMapper.selectOne(entity);
    }

    @Deprecated
    public List<T> findProperties(String condition, String... propertyName) throws Exception {
        Example example = new Example(mapperClass);
        Example.Criteria criteria = example.createCriteria();
        example.selectProperties(propertyName);
        criteria.andCondition(condition);
        return baseMapper.selectByExample(example);
    }


//    public List<T> findSelectProperty(String condition, String... selectProperty){
//        Example example=new Example(mapperClass);
//        //使用条件查询
//        Example.Criteria criteria=example.createCriteria();
//        if(selectProperty != null && selectProperty.length >= 1) {
//                //写入查询属性
//                example.selectProperties(selectProperty);
//            criteria.andCondition(condition);
//        }
//        else {
//            throw new RuntimeException("selectProperty!");
//        }
//        return baseMapper.selectByExample(example);
//
//    }


    public List<T> findSelectProperty(List<String> propertyList, List valueList, String... selectProperty) {
        Example example = new Example(mapperClass);
        //使用条件查询
        Example.Criteria criteria = example.createCriteria();
        if (selectProperty != null && selectProperty.length >= 1) {

            for (int e = 0; e < selectProperty.length; ++e) {
                //写入查询属性
                example.selectProperties(selectProperty);
            }
            for (int e = 0; e < propertyList.size(); ++e) {
                //写入条件,如username=zzf
                criteria.andEqualTo(propertyList.get(e),valueList.get(e));
            }
        } else {
            throw new RuntimeException("selectProperty!");
        }
        return baseMapper.selectByExample(example);
    }

    public List<T> findSelectProperty(String[] propertyList, String[] valueList, String... selectProperty) {
        Example example = new Example(mapperClass);
        //使用条件查询
        Example.Criteria criteria = example.createCriteria();

        for (int e = 0; e < selectProperty.length; ++e) {
            //写入查询属性
            example.selectProperties(selectProperty);
        }
        for (int e = 0; e < propertyList.length; ++e) {
            //写入条件,如username=zzf
                criteria.andEqualTo(propertyList[e],valueList[e]);
        }

        return baseMapper.selectByExample(example);
    }

    public List<T> findSelectPropertyWithOr(String property, String[] value, String... selectProperty) {
        Example example = new Example(mapperClass);
        //使用条件查询
        Example.Criteria criteria = example.createCriteria();
        for (int e = 0; e < selectProperty.length; ++e) {
            //写入查询属性
            example.selectProperties(selectProperty);
        }
        criteria.andEqualTo(property, value[0]);
        if (value.length >= 2) {
            for (int i = 1; i < value.length; i++) {
                example.or().andEqualTo(property, value[i]);
            }
        }


        return baseMapper.selectByExample(example);
    }

    public List<T> findSelectProperty(String propertyName, Object value, String... selectProperty) {
        if(value == null){
            throw new RuntimeException("value 不能为空");
        }
        Example example = new Example(mapperClass);
        //使用条件查询
        Example.Criteria criteria = example.createCriteria();
        if (selectProperty != null && selectProperty.length >= 1) {
            //写入查询属性
            example.selectProperties(selectProperty);
            criteria.andEqualTo(propertyName, value);
        }
        else {
            criteria.andEqualTo(propertyName, value);
        }
        return baseMapper.selectByExample(example);
    }

    public List<T> list(Object key) {
        return baseMapper.selectAll();
    }

    public int findCount(T entity)  {
        return baseMapper.selectCount(entity);
    }

    public int findCountByCondition(String value, String... propertyName)  {
        Example example = new Example(mapperClass);
        //使用条件查询
        Example.Criteria criteria = example.createCriteria();

        if (propertyName != null && propertyName.length == 1) {
            criteria.andLike(propertyName[0], "%" + value + "%");

        } else if (propertyName != null && propertyName.length > 1) {
            criteria.andLike(propertyName[0], "%" + value + "%");
            for (int i = 1; i < propertyName.length; i++) {
                Example.Criteria criteria1 = example.or();
                criteria1.andLike(propertyName[i], "%" + value + "%");
            }
        } else {
            throw new RuntimeException("propertyName不能为空!");
        }

        return baseMapper.selectCountByExample(example);
    }

    //    public List<T> listOrderByProperties(String order,String...propertyName){
//        Example example=new Example(mapperClass);
//        if(propertyName == null || propertyName.length == 0) {
//         propertyName[0]="id";
//        }
//        int fragment = propertyName.length - 1;
//        //默认降序
//        if((order == null || order.equals(""))||(order.equals("asc")))
//            for(int e = 0; e < fragment; ++e) {
//                example.orderBy(propertyName[e]);
//            }
//        else if (order.equals("desc")) {
//            for(int e = 0; e < fragment; ++e) {
//                example.orderBy(propertyName[e]).desc();
//            }
//        }
////        return baseMapper.selectByExample(example);
//        return null;
//    }
    //可通过一个或多个属性值条件进行查询，参数之间要一一对应
    public List<T> findByProperties(List<String> valueList, String... propertyName)  {
        Example example = new Example(mapperClass);
        //使用条件查询
        Example.Criteria criteria = example.createCriteria();
        if (propertyName != null && propertyName.length > 1) {
            if (valueList.isEmpty()) {
                throw new RuntimeException("valueList 不能为空");
            }
            for (int e = 0; e < propertyName.length; ++e) {
                //写入条件,如username=zzf
                criteria.andEqualTo(propertyName[e], valueList.get(e));
            }
        }  else if(propertyName.length == 1) {
                for (int i=0;i<valueList.size();i++) {
                    criteria.andEqualTo(propertyName[0], valueList.get(i));
                }
        }else {
            throw new RuntimeException("propertyName不能为空!");
        }
        return baseMapper.selectByExample(example);
    }

    public void deleteByIds(String... idArray) {
        Example example = new Example(mapperClass);
        //使用条件查询
        Example.Criteria criteria = example.createCriteria();

        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(idArray));
        criteria.andIn("id", set);
        baseMapper.deleteByExample(example);
    }

//    public List<T> pageEntityOrderByProperty(int index, int pageSize, String property) throws Exception {
//        return baseMapper.pageEntityOrderByProperty(pageSize * (index - 1), pageSize, property);
//    }
//
//    public List<T> pageEntityBySearchOrderByProperty(int index, int pageSize, String property, String condition) throws Exception {
//        return baseMapper.pageEntityBySearchOrderByProperty(pageSize * (index - 1), pageSize, property, condition);
//    }

    public List<T> listEntityByCondition(String condition, String... propertyName) {
        Example example = new Example(mapperClass);
        Example.Criteria criteria = example.createCriteria();
        if (propertyName != null && propertyName.length >= 1) {
            criteria.andLike(propertyName[0], "%" + condition + "%");
            for (int e = 1; e < propertyName.length; ++e) {
                Example.Criteria criteria1 = example.or();
                criteria1.andLike(propertyName[e], "%" + condition + "%");
            }

        } else {
            throw new RuntimeException("propertyName 不能为空");
        }
        return baseMapper.selectByExample(example);

    }

    public List<T> findAll()  {


        return baseMapper.selectAll();
    }

//    public void addBatch(List<T> list) throws Exception {
//
//        baseMapper.addBatch(list);
//    }


}
