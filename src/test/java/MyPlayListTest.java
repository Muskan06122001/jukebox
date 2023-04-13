
import DAO.MyPlaylist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;


public class MyPlayListTest {

    MyPlaylist myList;

    @Before
    public void setUp(){

        myList=new MyPlaylist();
    }

    @After
    public void tearDown(){
        myList=null;

    }


//    @Test
//    public void tableFailToCreate() throws SQLException {
//        String actual=myList.createdTableAllPlaylist();
//        Assert.assertNotEquals("Table not creates",actual);
//    }

}


