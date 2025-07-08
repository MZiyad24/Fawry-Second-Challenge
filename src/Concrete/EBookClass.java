package Concrete;

import Book.Book;
import Interface.EBook;

import java.time.Year;

public class EBookClass extends Book implements EBook {
    public String Type;

    public EBookClass(String ISBN, String title, Year productionYear, Double price, String Type) {
        super(ISBN, title, productionYear, price);
        this.Type=Type;
    }

    @Override
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
}
