package framework;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatabaseConn {
    public JdbcTemplate connection = null;

    public DatabaseConn(String url, String userName, String password){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin@//"+url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        connection = new JdbcTemplate(dataSource);
    }
}
