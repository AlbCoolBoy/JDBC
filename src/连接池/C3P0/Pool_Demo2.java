package 连接池.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Pool_Demo2 {
    public static void main(String[] args) {
        DataSource dataSource = new ComboPooledDataSource("default");
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = null;
            ResultSet resultSet=null;
            String sql_statement = "select * from customers";
            statement=connection.prepareStatement(sql_statement);
            resultSet=statement.executeQuery(sql_statement);
           while(resultSet.next()){
               String customer_id=resultSet.getString(1);
               String first_name=resultSet.getString(2);
               String last_name=resultSet.getString(3);
               String birth_date=resultSet.getString(4);
               String phone=resultSet.getString(5);
               String addrtess=resultSet.getString(6);
               String city=resultSet.getString(7);
               String state=resultSet.getString(8);
               String points=resultSet.getString(9);

               System.out.println(customer_id+" "+first_name+" "+last_name+""+birth_date
               +" "+birth_date+" "+phone+" "+addrtess+" "+city+" "+state+" "+points);
           }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
