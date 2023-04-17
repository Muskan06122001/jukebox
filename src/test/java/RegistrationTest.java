import dao.Registration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class RegistrationTest {


    Registration register;

    @Before
    public void setUp() {
        register = new Registration();
    }

    @After
    public void tearDown() {
        register = null;
    }

    @Test
    public void checkLoggedOut() {
        String actual = register.logout();
        Assert.assertEquals("**** You Are LoggedOut ****", actual);
    }

    @Test
    public void checkLoggedOutFailure() {
        String actual = register.logout();
        Assert.assertNotEquals("**** You not Are LoggedOut ****", actual);
    }

    @Test
    public void checkId() throws SQLException {
        int actual = register.userIdCheck("Muskan", "Muskan123");
        Assert.assertEquals(1, actual);
    }

    @Test
    public void checkIfFailure() throws SQLException {
        int actual = register.userIdCheck("Muskan", "Muskan123");
        Assert.assertNotEquals(2, actual);

    }

    @Test
    public void checkUserId() throws SQLException {
        int actual = register.userIdCheck("Mahek", "Mahek123");
        Assert.assertEquals(2, actual);

    }

    @Test
    public void checkUseIdFailure() throws SQLException {
        int actual = register.userIdCheck("Mahek", "Mahek123");
        Assert.assertNotEquals(1, actual);
    }
}

