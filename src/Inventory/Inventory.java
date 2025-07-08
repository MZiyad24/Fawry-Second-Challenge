package Inventory;

import java.time.Year;
import java.util.ArrayList;
import java.util.ListIterator;

import Book.Book;
import Interface.Demo;
import Interface.EBook;
import Interface.PaperBook;
import Services.MailService;
import Services.ShippingService;

public class Inventory {
    private ArrayList<Book> Books;

    private static Inventory store = new Inventory();

    private Inventory(){
        this.Books= new ArrayList<>();
    };

    public static Inventory getInventory(){
        return store;
    }

    public void addBook(Book book) {
        if(checkDate(book.getProductionYear()))
            throw new IllegalStateException("This book: "+book.getTitle()+" is outdated!!");
        if (!this.Books.isEmpty()) {
            ListIterator<Book> iterator = this.Books.listIterator();
            while(iterator.hasNext()) {
                Book item = (Book)iterator.next();
                if(item.getISBN().equals(book.getISBN())) {
                    if (item.equals(book)) {
                        if (item instanceof PaperBook) {
                            int size = ((PaperBook) item).getStockSize();
                            ((PaperBook) item).setStockSize(size + ((PaperBook) book).getStockSize());
                            throw new IllegalStateException("This book "+item.getTitle()+"'s inventory stock has been extended successfully");
                        } else {
                            throw new IllegalStateException("This book: "+item.getTitle()+" already exists!");
                        }
                    }
                    else{
                        throw new IllegalStateException("This book "+book.getTitle()+"'s ISBN already exists!");
                    }
                }
            }
        }
        Books.add(book);
        System.out.println("This book:\t"+book.getTitle()+" has been added successfully.\n");
    }

    private boolean checkDate(Year productionYear)
    {
        return Year.now().getValue() - productionYear.getValue() > 50;

    }

    public String removeOutDated(){
        String res="";
        ArrayList<Book> removedBooks = new ArrayList<Book>();
        if (!this.Books.isEmpty()) {
            ListIterator<Book> iterator = this.Books.listIterator();
            while(iterator.hasNext()) {
                Book item = (Book)iterator.next();
                if (checkDate(item.getProductionYear())) {
                    removedBooks.add(item);
                    iterator.remove();
                }
            }
        }
        if(removedBooks.size()>0)
        {
            res = "The Following Books are outdated and have been removed:\n";
            for(Book book : removedBooks)
            {
                res+=book.getTitle();
                res+='\n';
            }
        }
        else {
            res = "No outDated Books.";
        }
        return res;
    }

    public void Buy(String ISBN, int quantity , String email , String address ){
        if (!this.Books.isEmpty()) {
            ListIterator<Book> iterator = this.Books.listIterator();
            boolean exist =false;
            while(iterator.hasNext()) {
                Book item = (Book)iterator.next();
                if(item.getISBN().equals(ISBN)) {
                    exist=true;
                    if(item instanceof Demo)
                        throw new IllegalStateException("This book is a Demo version and is not for sale!");
                    if(item instanceof PaperBook)
                    {
                        ShippingService shippingService = new ShippingService();
                        if(quantity>((PaperBook) item).getStockSize())
                            throw new IllegalStateException("The required Quantity exceeds the available in stock!");
                        shippingService.ship(address,item);
                        ((PaperBook) item).setStockSize(((PaperBook) item).getStockSize()-quantity);
                    }
                    if(item instanceof EBook)
                    {
                        MailService mailService = new MailService();
                        mailService.mail(email,item);
                    }
                    System.out.println("** The Total Price **\n" +
                            "Book Price\t"+item.getPrice()+"$\n" +
                            "Quantity\t"+quantity+" copies\n" +
                            "Total Price is\t"+item.getPrice()*quantity+"$\n");
                    break;
                }
            }
            if(!exist){
                throw new IllegalStateException("The provided ISBN doesn't match any!\n");
            }
        }
        else {
            throw new IllegalStateException("The inventory is Empty!");
        }
    }

    public void removeBook(String ISBN){
        boolean exists =false;
        if (!this.Books.isEmpty()) {
            ListIterator<Book> iterator = this.Books.listIterator();
            while(iterator.hasNext()) {
                Book item = (Book)iterator.next();
                if (item.getISBN().equals(ISBN)) {
                    iterator.remove();
                    System.out.println(item.getTitle()+" has been removed from stock.\n");
                }
            }
            if(!exists)
                throw new IllegalStateException("No match found!");
        }
        else{
            throw new IllegalStateException("The inventory is Empty!");
        }
    }
}
