package itemControllerTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.ItemController;
import model.entities.*;

import static org.junit.jupiter.api.Assertions.fail;

// Test the searchItem function in several cases
public class SearchItemTest {
    ItemController itemController;
    private final static int MOCK_NEGATIVE = -1;
    private final static int MOCK_ZERO = 0;
    private final static int MOCK_POSITIVE = 1;
    private final static int MOCK_ID_EXIST = 2;
    private final static int MOCK_ID_NOT_EXIST = 200000;
    private final static int MOCK_SIZE_EXIST = 40;

    @BeforeEach
    public void setUp()
    {
        itemController = new ItemController();
    }

    @Test
    public void failIdTest() {
        try {
            itemController.searchItem(MOCK_NEGATIVE,MOCK_SIZE_EXIST);
            fail("The search succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void failSizeTest() {
        try {
            itemController.searchItem(MOCK_ID_EXIST,MOCK_NEGATIVE);
            fail("The search succeed when should failed");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("id or size must not be negative", e.getMessage());
        }
    }

    @Test
    public void SearchItemFoundedTest()
    {
        Item item = itemController.searchItem(MOCK_ID_EXIST,MOCK_SIZE_EXIST);
        Assertions.assertNotNull(item);
    }

    @Test
    public void SearchItemNotFoundSucceedTest()
    {
        Item item = itemController.searchItem(MOCK_ID_NOT_EXIST,MOCK_ID_NOT_EXIST);
        Assertions.assertNull(item);
    }
}