package purchaseControllerTests;

import controller.PurchaseController;
import model.entities.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test the lastPurchase function in several cases
public class LastPurchaseTest {
    PurchaseController purchaseController;
    private final static int MOCK_NEGATIVE = -1;
    private final static int MOCK_ZERO = 0;
    private final static int MOCK_POSITIVE = 1;
    private final static int MOCK_ID_NOT_EXIST = 9999;

    @BeforeEach
    public void setUp()
    {
        purchaseController = new PurchaseController();
    }

    @Test
    public void failMemberIdTest()
    {
        try {
            purchaseController.lastPurchase(MOCK_NEGATIVE);
            fail("The addition succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id must not be negative", e.getMessage());
        }
    }

    @Test
    public void failMemberIdOtherTest()
    {
        try {
            purchaseController.lastPurchase(MOCK_ZERO);
            fail("The addition succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("id must not be zero", e.getMessage());
        }
    }

    @Test
    public void failNullPurchaseTest()
    {
        Purchase p = purchaseController.lastPurchase(MOCK_POSITIVE);
        assertNotNull(p);
    }

    @Test
    public void failNotNullPurchaseTest()
    {
        Purchase p = purchaseController.lastPurchase(MOCK_ID_NOT_EXIST);
        assertNull(p);

    }
}
