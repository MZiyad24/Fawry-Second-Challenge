import Concrete.DemoBookClass;
import Concrete.EBookClass;
import Inventory.Inventory;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RemoveBookTest {
    private Inventory inventory = Inventory.getInventory();

    @Test
    public void successfulBookRemoval(){
        EBookClass eBook = new EBookClass("111","Algorithm Analysis", Year.of(1980),22.5,"PDF");
        inventory.addBook(eBook);
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        inventory.removeBook("111");
        System.setOut(originalOut);
        String output = outputStream.toString().trim();

        assertEquals(eBook.getTitle()+" has been removed from stock.",output);
    }

    @Test
    public void inventoryISEmpty(){

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            inventory.removeBook("111");
        });
        assertEquals("The inventory is Empty!",exception.getMessage());
    }

    @Test
    public void bookDoesntExist(){
        EBookClass eBook = new EBookClass("111","Algorithm Analysis", Year.of(1980),22.5,"PDF");
        inventory.addBook(eBook);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            inventory.removeBook("112");
        });
        assertEquals("No match found!",exception.getMessage());
    }

}
