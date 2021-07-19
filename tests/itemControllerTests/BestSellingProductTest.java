package itemControllerTests;
import controller.ItemController;
import model.entities.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test the bestSellingProduct function in one case
public class BestSellingProductTest {
    ItemController itemController;

    @BeforeEach
    public void setUp() {itemController = new ItemController();}

    @Test
    public void BestSellingProductFoundedTest()
    {
        Item item = itemController.bestSellingProduct();
        Assertions.assertNotNull(item);
    }
}