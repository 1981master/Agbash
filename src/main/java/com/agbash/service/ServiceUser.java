package com.agbash.service;

import com.agbash.component.Message;
import com.agbash.connection.DatabaseConnection;
import com.agbash.model.ModelLogin;
import com.agbash.model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ServiceUser {

    private final Connection con;
    private Message message;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
        message = new Message();
    }

    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            // Prepare the SQL statement
            p = con.prepareStatement("SELECT UserID, UserName, Email FROM `user` WHERE BINARY(Email)=? AND BINARY(`Password`)=? OR BINARY(`UserName`)=? AND `Status`='Verified' LIMIT 1");
            p.setString(1, login.getEmail());
            p.setString(2, login.getPassword());
            p.setString(3, login.getUserName());

            r = p.executeQuery();

            // Check if a record was found
            if (r.next()) { // Use r.next() to check for results
                int userID = r.getInt("UserID");
                String userName = r.getString("UserName");
                String email = r.getString("Email");
                data = new ModelUser(userID, userName, email, "");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during login: " + e.getMessage());

        } finally {

            if (r != null) {
                try {
                    r.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close ResultSet: " + e.getMessage());
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close PreparedStatement: " + e.getMessage());
                }
            }
        }
        return data;
    }

    private String generateVerifyCode() throws SQLException {
        Random ran = new Random();
        String code = String.valueOf(ran.nextInt(100000) + 1);

        return code;
    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
    System.out.println("Inside check email: " + user);
    boolean duplicate = false;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
        p = con.prepareStatement("select UserID from `user` where Email=? and `Status`='Verified'");
        p.setString(1, user);
        r = p.executeQuery();
        if (r.next()) {  // Use next() to move to the first row
            duplicate = true;
        }
    } catch (SQLException e) {
        System.err.println("FATAL: Error running query for checking duplicate emails\n" + e.getMessage());
        throw e;  // Re-throwing the exception to ensure it's not silently swallowed
    } finally {
        if (r != null) {
            try {
                r.close();
            } catch (SQLException e) {
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
        }
        if (p != null) {
            try {
                p.close();
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
    }
    return duplicate;
}


    public void doneVerify(int userID) throws SQLException {
        PreparedStatement p = null;
        try {
            p = con.prepareStatement("update `user` set VerifyCode='', `Status`='Verified' where UserID=? limit 1");
            p.setInt(1, userID);
            p.executeUpdate();  // Use executeUpdate for update queries
        } catch (SQLException e) {
            System.err.println("Error during verification update: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure it's properly handled or logged upstream
        } finally {
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        boolean verify = false;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            p = con.prepareStatement("SELECT UserID FROM `user` WHERE UserID=? AND VerifyCode=?");
            p.setInt(1, userID);
            p.setString(2, code);
            r = p.executeQuery();

            if (r.next()) {
                verify = true;
            }
        } catch (SQLException e) {
            System.out.println("Error in verifyCodeWithUser: " + e.getMessage());

        } finally {

            if (r != null) {
                try {
                    r.close();
                } catch (SQLException e) {
                    System.out.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    System.out.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
        return verify;
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        ResultSet r = null;
        PreparedStatement p = null;

        try {
            System.out.println("Preparing SQL statement...");

            p = con.prepareStatement(
                    "SELECT UserID FROM `user` WHERE UserName=?"
            );
            p.setString(1, user);

            System.out.println("Executing query with user: " + user);

            r = p.executeQuery();

            if (r.next()) {
                duplicate = true;
            }

            System.out.println("Duplicate found: " + duplicate);
        } catch (SQLException e) {
            System.out.println("========== SQL ERROR  ========\n" + e);
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (SQLException e) {
                    System.out.println("Error closing ResultSet: " + e);
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    System.out.println("Error closing PreparedStatement: " + e);
                }
            }
        }

        return duplicate;
    }

    public void insertUser(ModelUser user) throws SQLException {

        String code = generateVerifyCode();

        PreparedStatement p = null;
        if(user.getUserName().isBlank() || user.getPassword().isBlank() || user.getEmail().isBlank()){
            message.showMessage(Message.MessageType.ERROR, "Missing Field/s...");
            return;
        }
        try {
            p = con.prepareStatement(
                    "INSERT INTO `user` (UserName, Email, `Password`, VerifyCode) VALUES (?, ?, ?, ?)"
            );

            // Set parameters for the prepared statement
            p.setString(1, user.getUserName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, code);

            // Execute the insert
            int affectedRows = p.executeUpdate();

            if (affectedRows > 0) {

                try (PreparedStatement idStmt = con.prepareStatement(
                        "SELECT UserID FROM `user` WHERE UserName = ?"
                )) {
                    idStmt.setString(1, user.getUserName());
                    ResultSet r = idStmt.executeQuery();

                    if (r.next()) {
                        int userID = r.getInt("UserID");
                        user.setUserID(userID);
                    } else {
                        System.out.println("No user found after insert.");
                    }
                }
            } else {
                System.out.println("Insert operation failed; no rows affected.");
            }
        } catch (SQLException e) {

            System.out.println("Issue with DB Connection.");

        } finally {

            if (p != null) {
                p.close();
            }
        }
        user.setVerifyCode(code);
    }

}
