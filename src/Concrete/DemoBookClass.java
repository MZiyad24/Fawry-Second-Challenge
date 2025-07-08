package Concrete;

import Book.Book;
import Interface.Demo;

import java.time.Year;

public class DemoBookClass extends Book implements Demo {
    public DemoBookClass(String ISBN, String title, Year productionYear, Double price) {
        super(ISBN, title, productionYear, price);
    }
}
