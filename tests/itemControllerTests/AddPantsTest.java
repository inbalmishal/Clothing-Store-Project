package itemControllerTests;

import controller.ItemController;
import model.entities.Pants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// Test the add pants function in several cases
public class AddPantsTest {
    ItemController itemController;
    private final static int MOCK_NEGATIVE = -1;
    private final static int MOCK_ZERO = 0;
    private final static int MOCK_POSITIVE = 1;
    private final static int MOCK_ID_EXIST = 3;
    private final static int MOCK_SIZE_EXIST = 30;
    private final static int MOCK_ID_NOT_EXIST = 152;
    private final static int MOCK_SIZE_NOT_EXIST = 56;

    @BeforeEach
    public void setUp()
    {
        itemController = new ItemController();
    }

    @Test
    public void failIdTest() {
        try {
            Pants i = new Pants("blue", "niki", "men", "pants", 200, MOCK_POSITIVE, 200, 500, MOCK_NEGATIVE, "bermuda");
            itemController.addPants(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void failSizeTest() {
        try {
            Pants i = new Pants("blue", "niki", "men", "pants", 200, MOCK_NEGATIVE, 200, 500, MOCK_POSITIVE, "bermuda");
            itemController.addPants(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void failAddPantsTest() {
        try {
            Pants i = new Pants("blue", "niki", "men", "pants", 200, MOCK_SIZE_EXIST, 200, 500, MOCK_ID_EXIST, "bermuda");
            itemController.addPants(i);
            fail("The addition succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id and size exist", e.getMessage());
        }
    }

    @Test
    public  void failAddPantsSucceedTest()
    {
        Pants i = new Pants("blue", "niki", "men", "pants", 200, MOCK_SIZE_NOT_EXIST, 200, 500, MOCK_ID_NOT_EXIST, "bermuda");
        boolean a = itemController.addPants(i);
        assertTrue(a);
        itemController.deleteItem(MOCK_ID_NOT_EXIST,MOCK_SIZE_NOT_EXIST);
    }
}
