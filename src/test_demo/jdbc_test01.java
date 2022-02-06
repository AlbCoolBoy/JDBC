package test_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class jdbc_test01 {
    public static void main(String[] args) {
        /*使用资源帮顶器，注册驱动和连接更加灵活*/
        ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
        Connection connection=null;
        Statement statement=null;
        try {
            /*注册驱动*/
            Class.forName("com.mysql.cj.jdbc.Driver");  //这个操作不设置返回值的原因是只需要这个加载的执行动作
            /*创建连接*/
            String url = bundle.getString("url");
            String username = bundle.getString("username");
            String password = bundle.getString("password");
            connection = DriverManager.getConnection(url, username, password);
            //获取数据库对象
            statement = connection.createStatement();   //创建能够操作sql语句的对象

            //执行sql语句
            String sql_statement = "INSERT INTO creat_test ( username, age, sex, id_number, birth ) VALUES ( 'Tom Smith', 20, 'male', 202012034, 1986-12-09 )  ";
            //下面的方法专门执行（insert delete update），只能执行这三个啊
            //count返回值是影响数据库中的数据条数
            int count= statement.executeUpdate(sql_statement);
            System.out.println(count==1?"执行成功，插入"+count+"条数据":"执行失败");


            String sql_statement1="DELETE  FROM creat_test  WHERE username = 'Taylor' ";
            int count1=statement.executeUpdate(sql_statement1);
            System.out.println(count==1?"执行成功，删除"+count1+"条数据":"删除失败");

            String sql_statement2="update creat_test set username='Justin' where username='Jack' ";
            int count2=statement.executeUpdate(sql_statement2);
            System.out.println(count==1?"执行成功，更新"+count2+"条数据":"执行失败");

        } catch (ClassNotFoundException e) {    //注册驱动的异常抛出
            e.printStackTrace();
        } catch (SQLException e) {  //getconnection的异常抛出
            e.printStackTrace();
        } finally {
            //一定要在finally中释放资源,并且要遵循从小到大依次关闭
            //并且对每个关闭分别进行异常处理
            if (statement != null) {
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
