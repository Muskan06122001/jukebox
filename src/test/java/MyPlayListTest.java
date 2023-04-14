
import dao.MyPlaylist;
import org.junit.After;
import org.junit.Before;


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


