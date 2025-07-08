package Main;

import Concrete.DemoBookClass;
import Concrete.EBookClass;
import Concrete.PaperBookClass;
import Inventory.Inventory;

import java.time.Year;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInventory();
        EBookClass eBook = new EBookClass("123","Harry Poter", Year.of(1990),30.0,"PDF");
        DemoBookClass demoBook = new DemoBookClass("124","DB1", Year.of(1995),40.0);
        PaperBookClass paperBook = new PaperBookClass("125","Alice in Wonder Lands",Year.of(1980),42.5,30);

        inventory.addBook(eBook);
        inventory.addBook(demoBook);
        inventory.addBook(paperBook);

        inventory.Buy("123",1000,"mziyad154@gmail.com","Haram");
    }
}