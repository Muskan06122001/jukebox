import dao.SongDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class SongDAOException {
    SongDAO song;

    @Before
    public void setUp() {

        song = new SongDAO();
    }

    @After
    public void tearDown() {
        song = null;

    }

    @Test
    public void checkTable() throws SQLException {

        String actual = song.createTable();
        Assert.assertNotEquals("Table Already created", actual);
    }

}



