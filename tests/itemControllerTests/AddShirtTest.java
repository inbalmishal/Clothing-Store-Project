package itemControllerTests;

import controller.ItemController;
import model.entities.Shirt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// Test the add shirt function in several cases
public class AddShirtTest {
    ItemController itemController;
    private final static int MOCK_NEGATIVE = -1;
    private final static int MOCK_ZERO = 0;
    private final static int MOCK_POSITIVE = 1;
    private final static int MOCK_ID_EXIST = 5;
    private final static int MOCK_SIZE_EXIST = 20;
    private final static int MOCK_ID_NOT_EXIST = 5;

    @BeforeEach
    public void setUp()
    {
        itemController = new ItemController();
    }

    @Test
    public void failIdTest() {
        try {
            Shirt i = new Shirt("blue", "niki", "men", "shirt", 200, MOCK_POSITIVE, 200, 500, MOCK_NEGATIVE, "lycra");
            itemController.addShirt(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void failSizeTest() {
        try {
            Shirt i = new Shirt("blue", "niki", "men", "shirt", 200, MOCK_NEGATIVE, 200, 500,MOCK_POSITIVE, "lycra");
            itemController.addShirt(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void failAddShirtTest() {
        try {
            Shirt i = new Shirt("blue", "niki", "men", "shirt", 200, MOCK_SIZE_EXIST, 200, 500, MOCK_ID_EXIST, "bermuda");
            itemController.addShirt(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id and size exist", e.getMessage());
        }
    }

    @Test
    public  void failAddShirtSucceedTest()
    {
        Shirt i = new Shirt("blue", "niki", "men", "shirt", 200, MOCK_POSITIVE, 200, 500, MOCK_ID_NOT_EXIST, "bermuda");
        boolean a = itemController.addShirt(i);
        assertTrue(a);
        itemController.deleteItem(MOCK_ID_NOT_EXIST,MOCK_POSITIVE);
    }
}


