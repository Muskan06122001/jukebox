import dao.SongDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class SongDAOException {
    SongDao song;

    @Before
    public void setUp() {

        song = new SongDao();
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



