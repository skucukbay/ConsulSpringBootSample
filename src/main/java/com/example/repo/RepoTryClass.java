package com.example.repo;

import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by skucukbay on 7/26/16.
 */
@Component
public class RepoTryClass {

    private static final Logger log = LoggerFactory.getLogger(RepoTryClass.class);

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void testIt(){
        log.debug("postgresql version is: "+Driver.getVersion());
    }

    @Autowired
    public RepoTryClass(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        HikariDataSource ds = (HikariDataSource) this.jdbcTemplate.getDataSource();
        ds.setConnectionTestQuery("SELECT 1");
    }

    public void test(){
        jdbcTemplate.execute("set search_path to rmm");
        String sql = "select count(*) from alertresult";

        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        log.debug("count: "+integer);

    }
}
