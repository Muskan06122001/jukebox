package util;

import exception.TableAlreadyCreatedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class RegistrationDao {
    public String registrationTable() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        try {
            boolean res = st.execute("create table Registration(userName varchar(70),password varchar(80))");
            if (!res) {
                System.out.println("Table created");
            } else {
                throw new TableAlreadyCreatedException("Table already created");
            }

        } catch (TableAlreadyCreatedException | SQLSyntaxErrorException e) {
            System.out.println(e + "\n");
        }
        return "Table created";
    }

}



