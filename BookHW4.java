package edu.monmouth.hw4;

import edu.monmouth.book.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;

public class BookHW4 {
    public static void main(String args[]) throws BookException {
        if (args.length != 2) {
            System.err.println("Usage: java BookHW4 booksHW4.txt HW4output.txt");
            System.exit(1);
        }

        try {
            PrintStream fileOut = new PrintStream(new File(args[1]));
            System.setOut(fileOut);
            System.setErr(fileOut);
        } catch (FileNotFoundException e) {
            System.err.println("Output file not found: " + args[1]);
            System.exit(1);
        }

        HashSet<Book> bookSet = new HashSet<>();

        try {
            File dataFile = new File(args[0]);
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        int pages = Integer.parseInt(parts[2]);
                        double price = Double.parseDouble(parts[3]);
                        BookTypes type = BookTypes.valueOf(parts[1]);
                        Book book = new Book(pages, price, parts[0], type);
                        if (bookSet.add(book)) {
                            System.out.println("Successfully added book to HashSet: " + book);
                        } else {
                            System.out.println("Book was not added to HashSet (duplicate): " + book);
                        }
                    }
                } catch (IllegalArgumentException | BookException e) {
                    System.err.println("Invalid book data or book creation error: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found: " + args[0]);
            System.exit(1);
        }


        for (Book book : bookSet) {
            System.out.println(book);
        }

        checkBookInSet(bookSet, new Book(829, 23.95, "The Solomon Curse", BookTypes.HARDBACK));
        checkBookInSet(bookSet, new Book(629, 87.00, "The Big Bang Theory", BookTypes.HARDBACK));
    }

    private static void checkBookInSet(HashSet<Book> bookSet, Book bookToFind) {
        if (bookSet.contains(bookToFind)) {
		    System.out.println(bookToFind + "\nExists in bookSet");
		} else {
		    System.out.println(bookToFind + "\nDoes not exist in bookSet");
		}
    }
}
