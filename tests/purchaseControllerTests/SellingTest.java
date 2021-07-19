package purchaseControllerTests;

import controller.PurchaseController;
import model.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// Test the selling function with several tests
public class SellingTest {

    PurchaseController purchaseController;
    private final static int MOCK_NEGATIVE = -1;
    private final static int MOCK_ZERO = 0;
    private final static int MOCK_POSITIVE = 1;
    private final static int MOCK_ID_NOT_EXIST = 9999;
    private final static String MOCK_NAME = "yuval";
    private final static String MOCK_DATEOFBIRTH = "1990-3-31";
    private final static int MOCK_POINTSGAINED = 5550;
    private final static Member MOCK_EMPTY_MEMBER = new Member();
    private final static Member MOCK_MEMBER = new Member(MOCK_NAME, MOCK_DATEOFBIRTH, MOCK_POSITIVE, MOCK_POINTSGAINED);

    @BeforeEach
    public void setUp()
    {
        purchaseController = new PurchaseController();
    }

    @Test
    public void failMemberTest()
    {
        try {
            Item MOCK_ITEM = new Shoe("blue", "nabibas", "men", "shoe", 300, 40, 200, 50, 1,"bermuda");
            ArrayList<Item> MOCK_LIST = new ArrayList<Item>();
            MOCK_LIST.add(MOCK_ITEM);

            Purchase p = new Purchase(MOCK_EMPTY_MEMBER, MOCK_LIST, MOCK_POSITIVE, MOCK_POSITIVE);
            purchaseController.selling(p);
            fail("The selling succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("name or id or pointsgaind or dateofbirth must not be null or wrong", e.getMessage());
        }
    }

    @Test
    public void failArrayListTest()
    {
        try {
            ArrayList<Item> MOCK_LIST = new ArrayList<Item>();
            Purchase p = new Purchase(MOCK_MEMBER, MOCK_LIST, MOCK_POSITIVE, MOCK_POSITIVE);
            purchaseController.selling(p);
            fail("The selling succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("The Array list Items must not be null or empty", e.getMessage());
        }
    }

    @Test
    public void failPriceTest()
    {
        try {
            Item MOCK_ITEM = new Shoe("blue", "nabibas", "men", "shoe", 300, 40, 200, 50, 1,"bermuda");
            ArrayList<Item> MOCK_LIST = new ArrayList<Item>();
            MOCK_LIST.add(MOCK_ITEM);

            Purchase p = new Purchase(MOCK_MEMBER, MOCK_LIST, MOCK_NEGATIVE, MOCK_POSITIVE);
            purchaseController.selling(p);
            fail("The selling succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("the price or shoppingRating must not be negative", e.getMessage());
        }
    }

    @Test
    public void failSellingTest()
    {
        try {
            Item MOCK_ITEM = new Shoe("blue", "nabibas", "men", "shoe", 300, 40, 200, 50, 1,"bermuda");
            ArrayList<Item> MOCK_LIST = new ArrayList<Item>();
            MOCK_LIST.add(MOCK_ITEM);

            Purchase p = new Purchase(MOCK_MEMBER, MOCK_LIST, MOCK_POSITIVE, MOCK_NEGATIVE);
            purchaseController.selling(p);
            fail("The selling succeed when should failed");
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("the price or shoppingRating must not be negative", e.getMessage());
        }
    }

    @Test
    public void failSellingReturnTest()
    {
        Item MOCK_ITEM = new Shoe("blue", "nabibas", "men", "shoe", 300, 40, 200, 50, 1,"bermuda");
        ArrayList<Item> MOCK_LIST = new ArrayList<Item>();
        MOCK_LIST.add(MOCK_ITEM);

        Purchase p = new Purchase(MOCK_MEMBER, MOCK_LIST, MOCK_POSITIVE, MOCK_POSITIVE);
        int i = purchaseController.selling(p);
        assertTrue(i > -2);
    }
}
