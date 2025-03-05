package com.github.evseevda.pastebin.hashgenerator.util.db;

import com.github.evseevda.pastebin.hashgenerator.exception.FatalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseOperationsImpl implements DatabaseOperations {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new FatalException("Couldn't connect to PostgreSQL db.", e);
        }
    }

    @Override
    public List<Integer> getNIntegersFromSequence(int n, String sequenceName) {
        try (Connection connection = getConnection()) {
            return tryGenerate(n, sequenceName, connection);
        } catch (Throwable e) {
            throw new FatalException("Error while generating hash seeds.", e);
        }
    }

    private List<Integer> tryGenerate(int n, String sequenceName, Connection connection) throws SQLException {
        List<Integer> generated = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT nextval(?) FROM generate_series(1, ?)"
        );
        statement.setString(1, sequenceName);
        statement.setInt(2, n);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int nextInt = resultSet.getInt(1);
            generated.add(nextInt);
        }
        return generated;
    }

}
