## Spring JDBC

**Spring框架对JDBC进行了简单封装，提供了一个JDBCTemplate对象简化JDBC的开发**

### 步骤

1. 导入jar包



2. 创建JDBCTemplate对象，依赖于数据源DataSource

3. 调用JDBCTemplate的方法来完成CRUD的操作

    1. update():执行DML语句，即增删改语句
    2. queryForMap():执行查询语句，并将查询结果封装为Map集合
    3. queryForList(): 执行查询语句，并将查询结果封装为List集合
    4. query():执行查询语句，并将查询结果封装为JavaBean对象
    5. queryForObject():执行查询语句，并将查询结果封装为对象