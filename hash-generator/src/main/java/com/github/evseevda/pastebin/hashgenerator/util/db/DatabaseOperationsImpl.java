package com.github.evseevda.pastebin.hashgenerator.util.db;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DatabaseOperationsImpl implements DatabaseOperations {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Integer> getNIntegersFromSequence(int n, String sequenceName) throws DataAccessException {
        String sql = "SELECT nextval(?) FROM generate_series(1, ?)";
        return jdbcTemplate.queryForList(sql, Integer.class, sequenceName, n);
    }

}
