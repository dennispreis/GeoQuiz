import java.sql.*;

import static processing.core.PApplet.println;

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


    boolean isAccountExisting(String loginName, String loginPassword) {

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            String query = "select username, passcode from accounts where username = '" + loginName + "' AND passcode ='" + loginPassword + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();

            if (resultSet.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        println("Account not found: " + loginName + ":" + loginPassword);
        return false;
    }

    int getAccountId(String loginName, String loginPassword) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            String query = "select account_id, username, passcode from accounts where username = '" + loginName + "' AND passcode ='" + loginPassword + "' limit 1";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();

            if (resultSet.next()) return resultSet.getInt("account_id");
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}






