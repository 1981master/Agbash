

package com.agbash.agbash1;

import com.agbash.connection.DatabaseConnection;
import java.sql.SQLException;


public class Agbash1 {

    public static void main(String[] args) {
        
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Main main = new Main();
        main.dispaly();
        
        
    }
}
