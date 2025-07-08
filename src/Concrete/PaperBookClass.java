package Concrete;

import Book.Book;
import Interface.PaperBook;

import java.time.Year;

public class PaperBookClass extends Book implements PaperBook {
    public int stock;
    public PaperBookClass(String ISBN, String title, Year productionYear, Double price, int stock) {
        super(ISBN, title, productionYear, price);
        this.stock=stock;
    }

    @Override
    public int getStockSize() {
        return stock;
    }
    @Override
    public void setStockSize(int stock){
        this.stock=stock;
    }


}
