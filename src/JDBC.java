import java.sql.*;

public class JDBC {
    public void initializeDatabase() {
        //establish connection to sql server
        String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
        Connection con = null;
        try {
            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, Access.user, Access.pass);
            Statement st = con.createStatement();

            //Check if the database exists
            String sql = "SELECT * FROM sys.databases WHERE name='" + Access.databaseName + "'";
            ResultSet rs = st.executeQuery(sql);

            // Update url with the existing database name
            if (rs.next()) {
                url += ";databaseName=" + Access.databaseName;
                con = DriverManager.getConnection(url, Access.user, Access.pass);
                Statement st2 = con.createStatement();
                // Check if the table exists
                String sql1 = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'countries'";
                rs = st2.executeQuery(sql1);

                if (!rs.next()) {
                    // Create table if it doesn't exist
                    String sql2 = "CREATE TABLE countries (\r\n" + " ID INTEGER IDENTITY PRIMARY KEY,\r\n"
                            + "  Name VARCHAR(255),\r\n" + "Symbol VARCHAR(2)\r\n" + ");";
                    st2.executeUpdate(sql2);
                    System.out.println("TABLE CREATED!");
                } else {
                    System.out.println("Table already exists!");
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    public void removeTable() {
        String url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true";
        Connection con = null;
        try {
            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            url += ";databaseName=" + Access.databaseName;
            con = DriverManager.getConnection(url, Access.user, Access.pass);
            Statement st = con.createStatement();

            String sql = "DROP TABLE countries;";

            st.executeUpdate(sql);

            st.close();
            con.close();

            System.out.println("TABLE REMOVED!");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
