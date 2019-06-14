package com.rj.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * /**
 * @Nullable
 * private Map<Object, Object> targetDataSources;
 * @Nullable
 * private Object defaultTargetDataSource;
 *
 * resolvedDataSources这个数据结构是通过targetDataSources构建而来，存储的结构也是数据库标识和数据源的映射关系
 * @Nullable
 * private Map<Object, DataSource> resolvedDataSources;
 * @Nullable
 * private DataSource resolvedDefaultDataSource;
 *
 * @author ruanjin
 * @since 2019/5/22 11:54
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}
