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
                String sql1 = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME IN ('countries', 'borders', 'translations', 'languages', 'currencies', 'suffixes');";

                rs = st2.executeQuery(sql1);

                if (!rs.next()) {
                    // Create table if it doesn't exist
                    String sql2 = "CREATE TABLE countries (\r\n" + "	id int primary key,\r\n"
                            + "	common_name varchar(150),\r\n" + "	official_name varchar(150),\r\n" + "	cca2 varchar(150),\r\n"
                            + "	ccn3 varchar(150),\r\n" + "	cca3 varchar(150),\r\n" + "	cioc varchar(150),\r\n"
                            + "	independent bit,\r\n" + "	country_status varchar(150),\r\n" + "	un_member bit,\r\n"
                            + "	idd_root varchar(150),\r\n" + "	region varchar(150),\r\n" + "	subregion varchar(150),\r\n"
                            + "	latitude float,\r\n" + "	logitude float,\r\n" + "	land_locked bit,\r\n" + "	area float,\r\n"
                            + "	eng_f varchar(150),\r\n" + "	eng_m varchar(150),\r\n" + "	fra_f varchar(150),\r\n"
                            + "	fra_m varchar(150),\r\n" + "	flag varchar(150),\r\n" + "	google_maps varchar(150),\r\n"
                            + "	open_street_maps varchar(150),\r\n" + "	c_population int,\r\n" + "	gini_year varchar(150),\r\n"
                            + "	gini_val float,\r\n" + "	fifa varchar(150),\r\n" + "	car_side varchar(150),\r\n"
                            + "	flag_png varchar(150),\r\n" + "	flag_svg varchar(150),\r\n" + "	flag_alt text,\r\n"
                            + "	coa_png varchar(150),\r\n" + "	coa_svg varchar(150),\r\n" + "	start_of_week varchar(150),\r\n"
                            + "	capital_lat float,\r\n" + "	capital_long float,\r\n" + "	postal_format varchar(150),\r\n"
                            + "	postal_regex varchar(250)\r\n" + ");\r\n" + "\r\n" +
                            "CREATE TABLE borders(\r\n"
                            + "	id int primary key identity(1,1),\r\n"
                            + "	border varchar(150),\r\n" + "cid int\r\n" + ");\r\n" +
                            "CREATE TABLE translations(\r\n"
                            + "	id int primary key identity(1,1),\r\n" + "tran_key varchar(150),\r\n"
                            + "	tran_official varchar(150),\r\n" + "tran_common varchar(150),\r\n" + "cid int\r\n" + ");\r\n" +
                            "CREATE TABLE languages (\r\n"
                            + "	id int primary key identity(1,1),\r\n"
                            + "	lan_key varchar(150),\r\n" + "lan_name varchar(150),\r\n" + "cid int\r\n" + ");\r\n" +
                            "CREATE TABLE currencies(\r\n"
                            + "	id int primary key identity(1,1),\r\n" + "currency_key varchar(150),\r\n"
                            + "	currency_name varchar(150),\r\n" + "symbol varchar(150),\r\n" + "cid int\r\n" + ");\r\n" +
                            "CREATE TABLE suffixes(\r\n"
                            + "id int primary key identity(1,1),\r\n" + "suf varchar(150),\r\n"+ "cid int\r\n" + ");";
                    st2.executeUpdate(sql2);
                    System.out.println("TABLES CREATED!");
                } else {
                    //if table exits
                    System.out.println("Tables already exists!");
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

            String sql = "DROP TABLE countries, borders, translations, languages, currencies, suffixes;";

            st.executeUpdate(sql);

            st.close();
            con.close();

            System.out.println("TABLES REMOVED!");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
