
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class containing methods that establishes connection to DB, closes, and various queries
 * @author Paul Heintz
 */
public class DBCommands
{
    private static Connection conn;
    /**
     * Constructor that establishes connection to the database
     * @param filename database properties file
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public DBCommands(String filename) throws IOException, ClassNotFoundException, SQLException
    {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(filename);
        props.load(in);
       
        String driver = props.getProperty("jdbc.driver");
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        if (username == null) { username = ""; }
        String password = props.getProperty("jdbc.password");
        if (password == null) { password = ""; }
        if (driver != null) { Class.forName(driver); }
        conn = DriverManager.getConnection(url, username, password);
    }
    
    /**
     * Method to close the DB connection
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException
    {
        conn.close();
    }
    /**
     * SQL query of all values in Guest table
     * @return SQL result set
     * @throws SQLException 
     */
    public ResultSet queryAll() throws SQLException
    {
        String sql = "SELECT * FROM Guest";
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery(sql);
        return result;
    }
    /**
     * SQL to insert the name, message, and current_date into the database
     * @param name GUESTNAME column in the guest table
     * @param message MESSAGE column in the guest table
     * @return true if insert was successful
     * @throws SQLException 
     */
    public boolean insertEntry(String name, String message) throws SQLException
    {        
        String sql = "INSERT INTO APP.GUEST (GUESTNAME, MESSAGE, THEDATE)" + 
                "VALUES ('"+ name + "', '" + message + "', CURRENT_DATE)";
        Statement stat = conn.createStatement();
        boolean execute = stat.execute(sql);
        return execute;
    }
    /**
     * SQL statement to select all entries based on passed name
     * @param name name or portion of name to search the database for
     * @return result set
     * @throws SQLException 
     */
    public ResultSet searchName(String name) throws SQLException
    {
        String sql = "SELECT * FROM APP.GUEST WHERE UPPER(GUESTNAME) LIKE UPPER('%" + name + "%')";
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery(sql);
        return result;
    }
}
