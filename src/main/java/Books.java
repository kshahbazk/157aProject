/**
 * Created by shahbazkhan on 11/30/15.
 */
import java.sql.*;
public class Books {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Books";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "brolly91";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            stmt.execute("DROP DATABASE books");

            String sql = "CREATE DATABASE Books";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books" , "root" , "root");
            Statement myStmt = myConn.createStatement();

            tableGenerator tc= new tableGenerator(myStmt,myConn);
            tc.createTable();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample

