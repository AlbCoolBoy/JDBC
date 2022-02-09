package Template;

import org.springframework.jdbc.core.JdbcTemplate;
import 连接池.Util.Druid_Utils;

import java.sql.JDBCType;

public class Template_Demo {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate=new JdbcTemplate(Druid_Utils.getDataSource());        //这里传递的参数是一个DataSource的数据源
        //这里不需要再获取连接了，封装类会自动获取连接，下面直接创建sql语句，调用JDBCTemplate中的方法执行sql语句就行
        String sql="update t_user set password=123456 where id=?";
        int count= jdbcTemplate.update(sql,"202012093");
        System.out.println(count);

    }
}
