package 连接池.Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Druid_Demo {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        Properties properties=new Properties();
        InputStream is=Druid_Demo.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(is);

        //获取池连接对象
        DataSource dataSource=DruidDataSourceFactory.createDataSource(properties);

        Connection connection=dataSource.getConnection();
        System.out.println(connection);

        Statement statement=null;
        ResultSet resultSet=null;
        String sql_statement="select * from customers";
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql_statement);
        while(resultSet.next()){
            String customer_id=resultSet.getString(1);
            String first_name=resultSet.getString(2);
            String last_name=resultSet.getString(3);
            String birth_date=resultSet.getString(4);
            String phone=resultSet.getString(5);
            String address=resultSet.getString(6);
            String city=resultSet.getString(7);
            String state=resultSet.getString(8);
            String points=resultSet.getString(9);
            System.out.println(customer_id+'\t'+first_name+'\t'+last_name+'\t'+'\t'+'\t'+'\t'+birth_date+'\t'+phone+'\t'+
                    address+'\t'+city+'\t'+state+'\t'+points);
        }


    }
}
