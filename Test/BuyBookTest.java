import Concrete.DemoBookClass;
import Concrete.EBookClass;
import Concrete.PaperBookClass;
import Interface.EBook;
import Inventory.Inventory;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BuyBookTest {
    private Inventory inventory = Inventory.getInventory();

    @Test
    public void successfulBuy(){
        EBookClass eBook = new EBookClass("111","Algorithm Analysis", Year.of(1980),22.5,"PDF");
        inventory.addBook(eBook);
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        inventory.Buy("111",20,"mziyad154@gmail.com","Haram");
        System.setOut(originalOut);
        String output = outputStream.toString().trim();
        String expected = "\n** The Total Price **\n" +
                "Book Price\t"+eBook.getPrice()+"$\n" +
                "Quantity\t"+20+" copies\n" +
                "Total Price is\t"+eBook.getPrice()*20+"$";
        assertEquals(expected.trim(),output);
    }

    @Test
    public void demoBuy(){
        DemoBookClass demoBook = new DemoBookClass("111","Algorithm Analysis", Year.of(1980),22.5);
        inventory.addBook(demoBook);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            inventory.Buy("111",10,"mziyad154@gmail.com","Haram");
        });
        assertEquals("This book is a Demo version and is not for sale!",exception.getMessage());
    }

    @Test
    public void overAvailableQuantity(){
        PaperBookClass paperBook = new PaperBookClass("111","Algorithm Analysis", Year.of(1980),22.5,20);
        inventory.addBook(paperBook);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            inventory.Buy("111",30,"mziyad154@gmail.com","Haram");
        });
        assertEquals("The required Quantity exceeds the available in stock!",exception.getMessage());
    }

}
