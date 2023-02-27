import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ItemTest {
    @Test
    void testToString() {
        Item newItem=new Item("Test Item",200);
        String expectedString="Test Item:200\n";
        assertEquals(expectedString, newItem.toString());

    }
}
