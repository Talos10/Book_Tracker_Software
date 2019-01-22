// -----------------------------------------------------
// Assignment #1
// Question: part #2
// Written by: Razvan Ivan (40096278)
// -----------------------------------------------------

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Book [] inventory;
        int maxBooks;
        int choice;
        int passwordCycle = 0;
        int booksToEnter;
        int spaceAvailable;
        String title;
        String author;
        long ISBN;
        double price;
        int bookNumber;
        int filledUp = 0;
        int valueOfi = 0;

        Scanner readStr = new Scanner(System.in);//For reading strings
        Scanner readNum = new Scanner(System.in);//For reading numbers like ints, longs, doubles

        //Prints out a welcome message to the users.
        System.out.println("\n*******************************************************************************");
        System.out.println("\nWelcome to the BookTracker 2019! Keeping track of books has never been so easy!");
        System.out.println("\n*******************************************************************************");

        System.out.println("\nIn order to access all the features this program has to offer, we first need to know what is the maximal amount of books your store can keep!");
        System.out.println("\nPlease enter an integer value greater than 0 in order to set the inventory size: ");

        maxBooks = readNum.nextInt();

        while(maxBooks<=0){
            System.out.println("Invalid value for the inventory size. Please enter an integer value greater than 0: ");
            maxBooks = readNum.nextInt();
        }

        inventory = new Book[maxBooks];

        spaceAvailable = maxBooks;

        System.out.println("\nGreat! Now that we know what's our inventory size, we can get right down to business!");

        choice = menuChoiceChecker(1);

        outerloop:
        while(passwordCycle<=4 || choice == 5){
            switch(choice){

                case 1:
                    passwordCycle = passwordCheck(passwordCycle);

                    if(passwordCycle==0){
                        
                        while(choice==1){
                            if(spaceAvailable==0){
                                System.out.println("\nSorry but there is no more space to add books. You will now be redirected to the main menu in order to choose another option.");

                                break;
                            }

                            System.out.println("\nHow many books would you like to enter? The current available space is " + spaceAvailable + ". Enter 0 in order to be redirected to the main menu." );

                            booksToEnter = readNum.nextInt();

                            if(booksToEnter == 0)
                                break;

                            while(booksToEnter>spaceAvailable || booksToEnter<0) {
                                System.out.println("\nSorry but the value given for the amount of books to be entered is invalid.");
                                System.out.println("The value is either smaller or equal to 0 or the value exceeds the amount of space available which is " + spaceAvailable + ".");
                                System.out.println("Please re-enter the amount of books to enter.");
                                //System.out.println("Sorry but there is no space for " + booksToEnter + " books since there are only " + spaceAvailable + " places available. Please enter a number of books lower or equal to " + spaceAvailable + ": ");

                                booksToEnter = readNum.nextInt();
                            }

                            spaceAvailable = spaceAvailable - booksToEnter;

                            //Recheck values and how it works for spaceAvailable, filledup, booksToEnter


                            //Recheck was done but recheck that this for loop works for every case (like only 1 one book to add, one by one, and check that the books are not overwritten.)
                            for(int i = filledUp; i<filledUp + booksToEnter; i++){

                                System.out.println("\nPlease enter the title of book #" + i + ": ");

                                title = readStr.nextLine();

                                System.out.println("\nPlease enter the author of book #" + i + ": ");

                                author = readStr.nextLine();

                                System.out.println("\nPlease enter the ISBN of book #" + i + ": ");

                                ISBN = readNum.nextLong();
                                
                                while(ISBN<0){
                                    System.out.println("\nThe ISBN cannot be a negative number or non-integer number. Please re-enter the ISBN of the book: ");

                                    ISBN = readNum.nextLong();
                                }

                                System.out.println("\nPlease enter the price of book #" + i + ": ");

                                price = readNum.nextDouble();
                                
                                while(price<0){
                                    System.out.println("\nThe price cannot be a negative value. Please re-enter the price of the book: ");
                                    
                                    price = readNum.nextDouble();
                                }

                                inventory[i] = new Book(title, author, ISBN, price);

                                valueOfi = i;//To keep the value of i outside the for loop
                            }

                            filledUp = valueOfi + 1;//If no +1 then it overwrites the last book

                            System.out.println("\nYour books have been successfully registered into the system. To add more books, please enter 1. To return to the main menu, enter any number.");
                            
                            choice = readNum.nextInt();
                        }
                        
                        break;
                    }

                    else if(passwordCycle>0 && passwordCycle<=4)
                        break;

                case 2:
                    if(0!=passwordCheck(0))
                        break;

                    else{

                        do{
                            if(Book.findNumberOfCreatedBooks()==0){
                                System.out.println("\nThere is no book present yet in the registry and therefore there is no book to update. You will now be redirected to the main menu.");
                                break;
                            }

                            else {
                                if(Book.findNumberOfCreatedBooks() == 1){
                                    System.out.println("\nThere is currently only one book present in the registry and it is the book number #0.");
                                    System.out.println("If you wish to view the information about this book and/or update it, enter 1. To exit to the main menu, enter any number.");

                                    choice = readNum.nextInt();

                                    if(choice == 1)
                                        bookNumber = 0;

                                    else
                                        break;
                                }

                                else{
                                    System.out.println("\nThe present registry goes from book number #0 to book number #" + (Book.findNumberOfCreatedBooks() - 1)  + ". Enter the number of the book you wish to update (must be an integer greater or equal to 0): ");

                                    bookNumber = readNum.nextInt();
                                }

                            }
                            
                            while(bookNumber<0){
                                System.out.println("\nThe number of the book cannot be negative. Please re-enter the number of the book you wish to update (number must be greater or equal to 0): ");

                                bookNumber = readNum.nextInt();
                            }

                            //Makes sure null is the right thing to use here
                            if(maxBooks < bookNumber || inventory[bookNumber] == null){
                                System.out.println("\nThere is no book associated with this book number. To re-enter the number of another book, enter 1. To go back to the main menu, enter any number. ");

                                choice = readNum.nextInt();
                            }

                            else{
                                System.out.println("\nHere is the book associated with the book number #" + bookNumber + ":");

                                System.out.println(inventory[bookNumber]);

                                choice = menuChoiceChecker(2);

                                while(choice!=5){
                                    switch (choice){
                                        case 1:
                                            System.out.println("\nPlease enter the new name of the author: ");

                                            author = readStr.nextLine();

                                            inventory[bookNumber].setAuthor(author);

                                            System.out.println("\nThe change requested has been made. Here is the newly updated information about the book associated with the book number #" + bookNumber + ":");

                                            System.out.println(inventory[bookNumber]);

                                            System.out.println("\nWould you like to make some additional changes? If so, please enter 1. If you would like to quit to the main menu, enter any number.");
                                            
                                            choice = readNum.nextInt();
                                            
                                            if(choice==1)
                                                break;
                                            
                                            else{
                                                choice = 5;
                                                break;
                                            }
                                            
                                        case 2:
                                            System.out.println("\nPlease enter the new title of the book: ");

                                            title = readStr.nextLine();

                                            inventory[bookNumber].setTitle(title);

                                            System.out.println("\nThe change requested has been made. Here is the newly updated information about the book associated with the book number #" + bookNumber + ":");

                                            System.out.println(inventory[bookNumber]);

                                            System.out.println("\nWould you like to make some additional changes? If so, please enter 1. If you would like to quit to the main menu, enter any number.");

                                            choice = readNum.nextInt();

                                            if(choice==1)
                                                break;

                                            else{
                                                choice = 5;
                                                break;
                                            }
                                            
                                        case 3:
                                            System.out.println("\nPlease enter the new ISBN of the book: ");

                                            ISBN = readNum.nextLong();

                                            while(ISBN < 0){
                                                System.out.println("\nInvalid value for the ISBN (value cannot be negative). Please re-enter the new ISBN: ");

                                                ISBN = readNum.nextLong();
                                            }

                                            inventory[bookNumber].setISBN(ISBN);

                                            System.out.println("\nThe change requested has been made. Here is the newly updated information about the book associated with the book number #" + bookNumber + ":");

                                            System.out.println(inventory[bookNumber]);

                                            System.out.println("\nWould you like to make some additional changes? If so, please enter 1. If you would like to quit to the main menu, enter any number.");

                                            choice = readNum.nextInt();

                                            if(choice==1)
                                                break;

                                            else{
                                                choice = 5;
                                                break;
                                            }
                                            
                                        case 4:
                                            System.out.println("\nPlease enter the new price of the book: ");

                                            price = readNum.nextDouble();

                                            while(price < 0){
                                                System.out.println("\nInvalid value for the price (value cannot be negative). Please re-enter the new price: ");

                                                price = readNum.nextDouble();
                                            }

                                            inventory[bookNumber].setPrice(price);

                                            System.out.println("\nThe change requested has been made. Here is the newly updated information about the book associated with the book number #" + bookNumber + ":");

                                            System.out.println(inventory[bookNumber]);

                                            System.out.println("\nWould you like to make some additional changes? If so, please enter 1. If you would like to quit to the main menu, enter any number.");

                                            choice = readNum.nextInt();

                                            if(choice==1)
                                                break;

                                            else{
                                                choice = 5;
                                                break;
                                            }
                                    }
                                    if(choice == 5)
                                        break;

                                    choice = menuChoiceChecker(2);
                                }
                            }
                        }while(choice==1);

                        break;
                    }

                case 3:
                    do{
                        if(Book.findNumberOfCreatedBooks() == 0){
                            System.out.println("\nNo books have been entered yet into the system and therefore a search cannot take place. You will be redirected to the main menu.");
                            break;
                        }

                        else{
                            System.out.println("\nPlease enter the name of the author you would like to search books by: ");

                            author = readStr.nextLine();

                            findBooksBy(author, inventory);

                            System.out.println("\nWould you like to make another search with the name of another author? If so, please enter 1. If you would like to quit to the main menu, enter any number.");

                            choice = readNum.nextInt();
                        }

                    }while(choice == 1);

                    break;

                case 4:
                    do{
                        if(Book.findNumberOfCreatedBooks() == 0){
                            System.out.println("\nNo books have been entered yet into the system and therefore a search cannot take place. You will be redirected to the main menu.");
                            break;
                        }

                        else{
                            System.out.println("\nPlease enter the price : ");

                            price = readNum.nextDouble();

                            while(price <= 0){
                                System.out.println("\nInvalid value for the price (the value is either negative or equal to zero). Please re-enter the wanted price as a value greater than 0: ");

                                price = readNum.nextDouble();
                            }

                            findCheaperThan(price,inventory);

                            System.out.println("\nWould you like to make another search with a different price? If so, please enter 1. If you would like to quit to the main menu, enter any number.");

                            choice = readNum.nextInt();
                        }
                    }while(choice == 1);

                    break;

                case 5:
                    break outerloop;
            }

            if(passwordCycle == 4)
                break;

            choice = menuChoiceChecker(1);
            
        }

        if(passwordCycle == 4) {
            System.out.println("\n\n\n======================================================================");
            System.out.println("Program detected suspicious activities and will terminate immediately!");
            System.out.println("======================================================================");
        }

        //Prints out a closing message.
        System.out.println("\n\n\n**************************************************************************************************");

        System.out.println("\nThank you for using the BookTracker 2019, the best software there is for your book-tracking needs!");

        System.out.println("Please tell your friends about the BookTracker 2019!");

        System.out.println("\n**************************************************************************************************");

        System.out.println("\n\n********************************************************************");

        System.out.println("\nThis program was written by Razvan Ivan on the 24th of January 2019.");

        System.out.println("\n*************************END OF THE PROGRAM.************************");

        /*
        TESTING METHODS

        System.out.println(Book.findNumberOfCreatedBooks());

        Book book = new Book("The adventure of Tom Revere", "Raz IVVV", 12345, 60);

        System.out.println("title: "+book.getTitle());
        System.out.println("author: "+book.getAuthor());
        System.out.println("isbn: "+book.getISBN());
        System.out.println("price: "+book.getPrice());

        System.out.println(Book.findNumberOfCreatedBooks());

        book.setAuthor("Sup");
        book.setISBN(6969);
        book.setPrice(1);
        book.setTitle("Hol up");

        System.out.println(book);

        Book newBook = new Book("my mom", "your mom", 9595, 45);

        System.out.println(Book.findNumberOfCreatedBooks());

        Book newBook123 = new Book("my mom", "your mom", 6969, 1);

        System.out.println(book.equals(newBook));
        System.out.println(book.equals(newBook123));

        System.out.println(Book.findNumberOfCreatedBooks());

        */

    }

    public static int passwordCheck(int passwordCycle){

        int attempt=0;//Goes up to 3 and then a cycle is completed and then attempt goes back to 0
        //int cycle=0;//Goes up to 4 for a total of 12 attempts and then the program shuts down
        String actualPassword = "password";
        String password;

        Scanner keyboard = new Scanner(System.in);

        System.out.println("\nPlease enter the password: ");

        password = keyboard.nextLine();

        //while(cycle <3){
            while(attempt < 3){

                attempt++;

                if(password.equals(actualPassword))
                    return 0;

                else {
                    System.out.println("\nThe password entered is incorrect (attempt #" + attempt + ").");

                    if(attempt == 3 && passwordCycle != 3)
                        System.out.println("Redirecting to the main menu.");

                    //System.out.println("Password cycle: " + passwordCycle);

                    if (attempt < 3) {
                        System.out.println("\nPlease re-enter the password: ");

                        password = keyboard.nextLine();
                    }
                }
            }

            return passwordCycle + 1;

           // if(choice == 1)
            //    cycle++;

            //attempt = 0;
        //}




    }

    public static void displayMainMenu(){
        System.out.println("\nWhat do you wish to do?");
        System.out.println("\t1.\tEnter new books (password required)");
        System.out.println("\t2.\tChange information of a book (password required)");
        System.out.println("\t3.\tDisplay all books by a specific author");
        System.out.println("\t4.\tDisplay all books under a certain price");
        System.out.println("\t5.\tQuit");

        System.out.println("\nPlease enter your choice:");
    }

    public static void displayUpdateMenu(){
        System.out.println("\nWhat information would you like to change?");
        System.out.println("\t1.\tAuthor");
        System.out.println("\t2.\tTitle");
        System.out.println("\t3.\tISBN");
        System.out.println("\t4.\tPrice");
        System.out.println("\t5.\tQuit");

        System.out.println("\nPlease enter your choice:");
    }

    public static int menuChoiceChecker(int choiceOfMenu){
        Scanner key = new Scanner(System.in);
        int newChoice;

        if(choiceOfMenu==1)
            displayMainMenu();

        else
            displayUpdateMenu();

        newChoice = key.nextInt();

        while(newChoice!=1 && newChoice!=2 && newChoice!=3 && newChoice!=4 && newChoice!=5){
            System.out.println("\nInvalid choice. Please re-enter a valid choice from the list below.");

            if(choiceOfMenu==1)
                displayMainMenu();

            else
                displayUpdateMenu();

            newChoice = key.nextInt();
        }

        return newChoice;
    }

    public static void findBooksBy (String author, Book[] inventory){
        Boolean bool = true;

        for(int i=0; i<Book.findNumberOfCreatedBooks(); i++)
            if(inventory[i].getAuthor().equals(author)) {
                if(i==0)//So that this is only printed once at the beginning of the search if there is at least a book that matches with what the user wants.
                    System.out.println("\nHere is a list of all the books that are written by " + author + ": ");
                System.out.println(inventory[i]);
                bool = false;
            }

        if(bool)
                System.out.println("\nNo book has been found by this author.");
    }

    public static void findCheaperThan (Double price, Book[] inventory){
        Boolean bool = true;

        for(int i=0; i<Book.findNumberOfCreatedBooks(); i++)
            if(inventory[i].getPrice() < price) {
                if(i==0)//So that this is only printed once at the beginning of the search if there is at least a book that matches with what the user wants.
                    System.out.println("\nHere is a list of all the books that are under $" + price + ": ");

                System.out.println(inventory[i]);
                bool = false;
            }

        if(bool)
                System.out.println("\nNo book has been found below the specified price.");
    }
}
