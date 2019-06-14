package com.rj.config;

import com.rj.common.DatabaseType;

/**
 * @author ruanjin
 * @since 2019/5/22 11:55
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
}
