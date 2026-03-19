package com.axenovo.infotech.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnectivityVerifier {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConnectivityVerifier.class);

    private final DataSource dataSource;

    @Autowired
    public DatabaseConnectivityVerifier(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void verify() {
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            String user = connection.getMetaData().getUserName();
            log.info("PostgreSQL connectivity verified: url=[{}], user=[{}]", url, user);
        } catch (SQLException ex) {
            log.error("Unable to connect to PostgreSQL with configured datasource", ex);
            throw new IllegalStateException("PostgreSQL connection verification failed", ex);
        }
    }
}
