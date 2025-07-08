import Concrete.DemoBookClass;
import Concrete.EBookClass;
import Concrete.PaperBookClass;
import Inventory.Inventory;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AddBookTest {
    private Inventory inventory = Inventory.getInventory();

    @Test
    public void successfulBookAddition(){
        EBookClass eBook = new EBookClass("111","Algorithm Analysis", Year.of(1980),22.5,"PDF");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        inventory.addBook(eBook);
        System.setOut(originalOut);
        String output = outputStream.toString().trim();

        assertEquals("This book:\t"+eBook.getTitle()+" has been added successfully.",output);
    }

    @Test
    public void alreadyExistsBook(){
        DemoBookClass demoBook = new DemoBookClass("124","DB1", Year.of(1995),40.0);
        inventory.addBook(demoBook);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            inventory.addBook(demoBook);
        });
        assertEquals("This book: "+demoBook.getTitle()+" already exists!", exception.getMessage());
    }

    @Test
    public void existedISBN(){
        DemoBookClass demoBook = new DemoBookClass("124","DB1", Year.of(1995),40.0);
        EBookClass eBook = new EBookClass("124","Algorithm Analysis", Year.of(1980),22.5,"PDF");

        inventory.addBook(demoBook);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            inventory.addBook(eBook);
        });
        assertEquals("This book "+eBook.getTitle()+"'s ISBN already exists!",exception.getMessage());
    }



    @Test
    public void outDatedBookAddition(){
        EBookClass eBook = new EBookClass("111","Algorithm Analysis", Year.of(1950),22.5,"PDF");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            inventory.addBook(eBook);
        });
        assertEquals("This book: "+eBook.getTitle()+" is outdated!!",exception.getMessage());

    }

}
