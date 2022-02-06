package test_demo;

import java.sql.*;
import java.util.ResourceBundle;

public class jdbc_test02 {
    //处理查询结果集！！！
    public static void main(String[] args) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        ResourceBundle bundle=ResourceBundle.getBundle("jdbc");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url=bundle.getString("url");
            String username= bundle.getString("username");
            String password=bundle.getString("password");
            connection= DriverManager.getConnection(url,username,password);

            statement=connection.createStatement();

            String sql_statement="select * from creat_test";
            resultSet=statement.executeQuery(sql_statement);      //专门处理查询语句
            while(resultSet.next()){
                String name=resultSet.getString("username");             //某一行的第一列数据
                String age=resultSet.getString("age");              //某一行的第二列数据
                String sex=resultSet.getString("sex");              //某一行的第三列数据
                String id_number=resultSet.getString("id_number");        //某一行的第四列数据
                String birth=resultSet.getString("birth");            //这里传递的参数也可以直接写字段名
                System.out.println(name+" "+age+","+sex+","+id_number+","+birth);

            }

            while (resultSet.next()){
                String name=resultSet.getString("username");
                String age=resultSet.getString("age");
                String sex=resultSet.getString("sex");
                String id_number=resultSet.getString("id_number");
                String birth=resultSet.getString("birth");
                System.out.println(name+" "+age+","+sex+","+id_number+","+birth);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //释放资源
        finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
