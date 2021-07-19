package itemControllerTests;

import controller.ItemController;
import model.entities.Shoe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// Test the add shoe function in several cases
public class AddShoeTest {
    ItemController itemController;
    private final static int MOCK_NEGATIVE = -1;
    private final static int MOCK_ZERO = 0;
    private final static int MOCK_POSITIVE = 1;
    private final static int MOCK_ID_EXIST = 2;
    private final static int MOCK_SIZE_EXIST = 40;
    private final static int MOCK_ID_NOT_EXIST = 80;

    @BeforeEach
    public void setUp()
    {
        itemController = new ItemController();
    }

    @Test
    public void failIdTest() {
        try {
            Shoe i = new Shoe("blue", "niki", "men", "shoe", 200, MOCK_POSITIVE, 200, 500, MOCK_NEGATIVE, "lycra");
            itemController.addShoe(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void failSizeTest() {
        try {
            Shoe i = new Shoe("blue", "niki", "men", "shoe", 200, MOCK_NEGATIVE, 200, 500, MOCK_POSITIVE, "lycra");
            itemController.addShoe(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void failAddShoeTest() {
        try {
            Shoe i = new Shoe("blue", "niki", "men", "shoe", 200, MOCK_SIZE_EXIST, 200, 500, MOCK_ID_EXIST, "bermuda");
            itemController.addShoe(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id and size exist", e.getMessage());
        }
    }

    @Test
    public  void failAddShoeSucceedTest()
    {
        Shoe i = new Shoe("blue", "niki", "men", "shoe", 200, MOCK_POSITIVE, 200, 500, MOCK_ID_NOT_EXIST, "bermuda");
        boolean a = itemController.addShoe(i);
        assertTrue(a);
        itemController.deleteItem(MOCK_ID_NOT_EXIST,MOCK_POSITIVE);
    }
}




