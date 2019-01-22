// -----------------------------------------------------
// Assignment #1
// Question: part #1
// Written by: Razvan Ivan (40096278)
// -----------------------------------------------------

public class Book {

    private String title;
    private String author;
    private long ISBN;
    private double price;
    private static int createdBooks = 0;

    public Book (String title, String author, long ISBN, double price){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        createdBooks++;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public long getISBN(){
        return ISBN;
    }

    public double getPrice(){
        return price;
    }

    public void setTitle(String title){
        this.title = title;
        //this.title = title.toUpperCase().charAt(0) + title.toLowerCase().substring(1, title.length());
    }

    public void setAuthor(String author){
        this.author = author;
        //this.author = author.toUpperCase().charAt(0) + author.toLowerCase().substring(1, author.length());
    }

    public void setISBN(long ISBN){
        this.ISBN = ISBN;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String toString(){
        return  "\nAuthor: " + author +"\nTitle: " + title  + "\nISBN: " + ISBN + "#" + "\nPrice: $" + price;
    }

    public static int findNumberOfCreatedBooks(){
        return createdBooks;
    }

    public boolean equals(Book book){
        return ISBN == book.ISBN && price == book.price;
    }


}
