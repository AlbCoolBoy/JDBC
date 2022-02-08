package 连接池.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Pool_Demo {
    public static void main(String[] args) throws SQLException {
        //创建数据库连接池对象
        DataSource dataSource=new ComboPooledDataSource();
        //获取连接对象
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
    }
}
