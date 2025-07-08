package Book;

import java.time.LocalDate;
import java.time.Year;

public class Book {
    protected String ISBN;
    protected String Title;
    protected Year ProductionYear;
    protected Double Price;


    public Book(String ISBN, String title, Year productionYear, Double price) {
        this.ISBN = ISBN;
        Title = title;
        ProductionYear = productionYear;
        Price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Year getProductionYear() {
        return ProductionYear;
    }

    public void setProductionYear(Year productionYear) {
        ProductionYear = productionYear;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
