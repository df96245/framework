package scau.zzf.base.aop;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component
@Aspect
public class CheckServiceNameAspect {
    private Logger logger = LogManager.getLogger(CheckServiceNameAspect.class);

    @Around("execution(* scau.zzf.service..*.*(..))")
    public Object methodCheck(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().getName();
        if(isValidServiceMethodForTx(methodName)){
            return pjp.proceed();
        }
        logger.error("=====================================================");
        logger.error("==出于事务管理原因，Service方法只能使用以下前缀命名的方法名==");
        logger.error("==新增方法：add*==");
        logger.error("==删除方法：delete*==");
        logger.error("==修改方法：update*==");
        logger.error("==查询单个值方法：find*==");
        logger.error("==查询多个值方法：list*==");
        logger.error("==查询分页方法：page*==");
        logger.error("==查询数量方法：count*==");
        logger.error("=====================================================");
        throw new Exception();
    }

    private Boolean isValidServiceMethodForTx(String methodName){
        if(methodName.startsWith("add")
                || methodName.startsWith("update")
                || methodName.startsWith("delete")
                || methodName.startsWith("find")
                || methodName.startsWith("list")
                || methodName.startsWith("page")
                || methodName.startsWith("count")){
            return true;
        }
        return false;
    }
}
