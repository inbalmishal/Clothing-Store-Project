package generalOpControllerTests;

import controller.GeneralOpController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test the login function in several cases
public class LoginTest {
    GeneralOpController generalOpController;
    private final static int MOCK_NEGATIVE = -1;
    private final static int MOCK_ZERO = 0;
    private final static int MOCK_POSITIVE = 1;

    @BeforeEach
    public void setUp()
    {
       generalOpController = new GeneralOpController();
    }

    @Test
    public void failLoginNegativeIdTestManager()
    {
        try {
            generalOpController.isManager(MOCK_NEGATIVE, "1234");
            fail("Login succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id or password must not be null or wrong", e.getMessage());
        }
    }

    @Test
    public void failLoginZeroIdTestManager()
    {
        try {
            generalOpController.isManager(MOCK_ZERO, "1234");
            fail("Login succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id or password must not be null or wrong", e.getMessage());
        }
    }

    @Test
    public void failLoginEmptyPasswordTestManager()
    {
        try {
            generalOpController.isManager(MOCK_POSITIVE, "");
            fail("Login succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id or password must not be null or wrong", e.getMessage());
        }
    }

    @Test
    public void loginSucceedTestManager()
    {
        boolean checkTrue = generalOpController.isManager(12, "1234");
        assertTrue(checkTrue);
        boolean checkFalse = generalOpController.isManager(1, "1111");
        assertFalse(checkFalse);
    }

    @Test
    public void failLoginNegativeIdTestWorker()
    {
        try {
            generalOpController.isWorker(MOCK_NEGATIVE, "1234");
            fail("Login succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id or password must not be null or wrong", e.getMessage());
        }
    }

    @Test
    public void failLoginEmptyPasswordTestWorker()
    {
        try {
            generalOpController.isWorker(MOCK_POSITIVE, "");
            fail("Login succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id or password must not be null or wrong", e.getMessage());
        }
    }

    @Test
    public void failLoginZeroIdTestWorker()
    {
        try {
            generalOpController.isWorker(MOCK_ZERO, "1234");
            fail("Login succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id or password must not be null or wrong", e.getMessage());
        }
    }

    @Test
    public void loginSucceedTestWorker()
    {
        boolean checkTrue = generalOpController.isWorker(10, "0404");
        assertTrue(checkTrue);
        boolean checkFalse = generalOpController.isWorker(1, "1111");
        assertFalse(checkFalse);
    }

}
