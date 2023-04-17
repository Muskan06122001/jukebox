package dao;

import util.Dbutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import exception.TableAlreadyCreatedException;

public class RegistrationDao {
    public String RegistrationTable() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        try {
            boolean res = st.execute("create table Registration(userName varchar(70),password varchar(80))");
            if (!res) {
                System.out.println("Table created");
            } else {
                throw new TableAlreadyCreatedException("Table already created");
            }

        }catch (TableAlreadyCreatedException |  SQLSyntaxErrorException  e){
            System.out.println(e+"\n");
        }
        return "Table created";
    }

}



