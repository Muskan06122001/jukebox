package util;

import exception.TableAlreadyCreatedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class MyPlaylistDao {

    public String createdTableAllPlaylist() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        try {
            boolean res = st.execute("create table MyAllPlayList(UserId int ,NameOfPlayList varchar(100),SongId int ,foreign key(SongId) references mysongs(SongId))");
            if (!res) {
                System.out.println("Successful");
            } else {
                throw new TableAlreadyCreatedException("Table Already created");
            }

        } catch (TableAlreadyCreatedException | SQLSyntaxErrorException e) {
            System.out.println("Table already Exist" + e.getMessage());
        }
        return "Table created";
    }


}
