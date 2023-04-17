import util.RegistrationDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class RegistrationDAOException {



    RegistrationDao rd;


    @Before
    public void setUp(){
        rd=new RegistrationDao();

    }
    @After
    public void tearDown(){
        rd=null;
    }


    @Test
    public void createTableFailure() throws SQLException {

        String actual=rd.RegistrationTable();
        Assert.assertNotEquals("Table already created",actual);

    }

}
