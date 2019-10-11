import java.sql.*;

class DBConnector {

    private String url;
    private String user;
    private String password;
    private String driver;

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    DBConnector() {
        this.url = "jdbc:mysql://localhost:3306/geo_quiz";
        this.user = "root";
        this.password = "";
        this.driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testingConnection() {

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM accounts");
            while(resultSet.next()) {
                System.out.println("Usernames");
                System.out.println(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}






