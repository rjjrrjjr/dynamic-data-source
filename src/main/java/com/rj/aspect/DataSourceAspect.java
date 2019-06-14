package com.rj.aspect;

import com.rj.common.DatabaseType;
import com.rj.config.DatabaseContextHolder;
import com.rj.mapper.UserEntityMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ruanjin
 * @since 2019/5/22 15:38
 */
@Component
@Aspect
public class DataSourceAspect {

    @Pointcut("execution(* com.rj.mapper.*.*(..))")
    public void declareJoinPointExpression(){
    }

    @Before("declareJoinPointExpression()")
    public void setDataSourceKey(JoinPoint jointPoint){
        if (jointPoint.getTarget() instanceof UserEntityMapper){
            DatabaseContextHolder.setDatabaseType(DatabaseType.DATABASE_TWO);
        }else {
            DatabaseContextHolder.setDatabaseType(DatabaseType.DATABASE_ONE);
        }
    }
}
