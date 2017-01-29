
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * MainMethod class contains the entry point to the program and acts as the 
 * program controller 
 * @author Paul Heintz
 */
public class MainMethod
{
    static JFrame menu = new MainMenu();
    static JFrame entry;
    static JFrame search;
    private static String filename = "";
    
    public static void main(String[] args)
    {
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
        if (args.length == 0)
        {
            System.out.println("Usage: java -classpath driver_class_path" 
                              + File.pathSeparator 
                              + ". Guestbook propertiesFile");
            return;
        }
        filename = args[0];
    }
    /**
     * Method that hides the Main menu navigation and opens the entry form
     * @throws IOException 
     */
    public static void showEntryForm() throws IOException
    {
        entry = new EntryForm(filename);
        menu.setVisible(false);
        entry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entry.setVisible(true);
    }
    /**
     * Method that hides the main menu form and opens the search form
     * @throws IOException
     * @throws ClassNotFoundException 
     */    
    public static void showSearchForm() throws IOException, ClassNotFoundException
    {
        search = new SearchForm(filename);
        menu.setVisible(false);
        search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search.setVisible(true);
    }
    /**
     * Method that closes either the entry form or search form and unhides the 
     * main menu
     * @param caller tells the method which form needs to be closed
     */
    public static void cancelOperation(String caller)
    {
        if (caller.equals("entry"))
        {
            entry.dispose();
        }
        else if (caller.equals("search"))
        {
            search.dispose();
        }
        menu.setVisible(true);
    }
}
