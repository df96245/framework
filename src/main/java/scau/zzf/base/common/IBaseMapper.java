package scau.zzf.base.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
/**
 * Created by zzf on 2016/11/21.
 */
@Repository
public interface IBaseMapper<T> extends Mapper<T> {




}
