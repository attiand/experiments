package org.entity;

import io.agroal.api.AgroalDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Dependent
public class TestDAO {

    private final DataSource dataSource;

    @Inject
    TestDAO(AgroalDataSource defaultDataSource) {
        this.dataSource = defaultDataSource;
    }

    @PostConstruct
    public void init() {
        /*
        try(Connection con = dataSource.getConnection()) {
            Statement st = con.createStatement();
            var res = st.execute("""
                CREATE TABLE cars (
                    brand VARCHAR(255),
                    model VARCHAR(255),
                    year INT
                );
            """);
            System.out.println("res: " + res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         */
    }


    public void set(String model) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(" INSERT INTO cars (brand, model, year) VALUES (?, ?, ?)")) {
                ps.setString(1, "Ford");
                ps.setString(2, model);
                ps.setInt(3, 1965);

                if (ps.executeUpdate() == 1) {
                    System.out.println("update ok");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void get(String key) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM cars")) {
                if (ps.execute()) {
                    try (ResultSet rs = ps.getResultSet()) {
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + " --- " + rs.getString(2));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
