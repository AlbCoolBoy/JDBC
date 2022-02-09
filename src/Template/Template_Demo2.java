package Template;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import 连接池.Util.Druid_Utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Template_Demo2 {

    //使用Junit测试单元，可以让方法独立执行

    @Test
    public void test01() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Druid_Utils.getDataSource());
        String sql = "update t_user set password=? where id=?";
        int count = jdbcTemplate.update(sql,"271824","202012034");
        System.out.println(count);
    }

    @Test
    public void test02(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(Druid_Utils.getDataSource());
        String sql="insert into t_user (id,username,password) values(?,?,?)";
        int count=jdbcTemplate.update(sql,"202012022","justin","314159");
        System.out.println(count);
    }

    @Test                       //查询一个记录将其封装为Map
    public void test03(){       //这个方法查询出来的结果集只能是一条记录
        JdbcTemplate jdbcTemplate=new JdbcTemplate(Druid_Utils.getDataSource());
        String sql="select * from customers where state=?";
        Map<String,Object> query_map=jdbcTemplate.queryForMap(sql,"CO");
        System.out.println(query_map);
    }

    @Test
    public void test04(){       //使用这个能将每一条记录封装成Map，然后再将所有的记录封装成List
        JdbcTemplate jdbcTemplate=new JdbcTemplate(Druid_Utils.getDataSource());
        String sql="select * from customers";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        for(Map<String,Object> stringObjectMap: list){
            System.out.println(stringObjectMap);
        }
    }

    //执行聚合函数
    @Test
    public void test05(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(Druid_Utils.getDataSource());
        String sql="select count(last_name) from customers";
        Long total=jdbcTemplate.queryForObject(sql,Long.class);     //获取聚合函数的返回值
        System.out.println(total);
    }

    @Test       //使用template.query将查询结果封装成JavaBean对象
    public void test06(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(Druid_Utils.getDataSource());
        String sql="select * from customers";
        List<Emp> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
    //JavaBean对象就是一个具有set和get方法，也就是读写权限的对象


}


























