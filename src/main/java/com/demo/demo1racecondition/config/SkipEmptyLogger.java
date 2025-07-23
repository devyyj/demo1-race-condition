package com.demo.demo1racecondition.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.P6Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 빈 SQL(null/blank) 및 SQL 실행 이벤트(statement)가 아닌 경우를 스킵하고,
 * 오직 완성된 Statement(SQL)만 SLF4J INFO로 출력합니다.
 */
public class SkipEmptyLogger implements P6Logger {

    private static final Logger log = LoggerFactory.getLogger("p6spy");

    @Override
    public void logSQL(
            int connectionId,
            String now,
            long elapsed,
            Category category,
            String prepared,
            String sql,
            String url) {

        // 1) Statement 이벤트가 아니면 무시 (commit/rollback/resultset 등 제외)
        if (category != Category.STATEMENT) {
            return;
        }
        // 2) SQL 문자열이 비어 있으면 무시
        if (sql == null || sql.trim().isEmpty()) {
            return;
        }
        // 3) 완성된 SQL만 출력
        log.info(sql.trim());
    }

    @Override
    public void logException(Exception e) {
        // 필요 없으면 무시
    }

    @Override
    public void logText(String text) {
        // 필요 없으면 무시
    }

    @Override
    public boolean isCategoryEnabled(Category category) {
        // Statement 이벤트만 로깅 대상
        return category == Category.STATEMENT;
    }
}
