# JDBC
* 引入驱动程序。
	* jar，类库，对SQL规范的实现。
## 一般过程
* 使用JDBC的过程
	1. 注册驱动程序。````Class.forName("com.mysql.jdbc.Driver");````
	2. 获得连接。````DriverManager.getConnection(url,user,pass);````
	3. 创建Statement对象。````conn.createStatement();````
	4. 调用Statement执行sql语句。````st.excute(sql);````
	5. 查找需要ResultSet。````ResultSet = st.excute(sql);````
	6. 遍历ResultSet。
## Statement
1. 存在SQL注入
	* 存在特殊的输入，导致SQL语句执行有误。
	* 主要存在于转义字符
2. 执行效率低
	* 执行过程由客户端发送到服务器
	* 服务器进行语法分析，语义分析，执行
## PreparedStatement 预编译语句
1. 防止SQL注入
	* 对转移字符进行自动转义
2. 执行高效，预先编译好，服务器无须再进行编译
3. 批量插入速度快
	1. addBatch()；生成批量语句
	2. excuteBatch()；执行批量语句
	3. clearBatch()；清除批量
	4. 控制每批个数，提高执行效率。
## callableStatement
* 负责调用数据库的存储过程（函数）
* 非常快
## 在java中调用存储过程
1. 首先编写存储过程的调用语句。````{call name(?...)}````
2. 创建CallableStatement对象。````CallableStatement cst = conn.prepareCall(sql);````
3. 设置输入输出，输出需要注册。````cst.registerOutParameter(1, Types.INTEGER);````
4. 执行sql语句
5. 获取输出结果。
## 设置隔离级别
````conn.setTransactionIsolation(Connection.X);````
## 连接池 
* 将时间花费集中于SQL语句的执行，一次创建多个连接，放置在连接池中供客户端使用。
* java.sql.DataSource 内部连接池
* 自定义连接池
	* 类图：![](http://i.imgur.com/zyTGrRe.png)
	* CollectionPool类封装了List<Connection>集合。提供移除和添加的方法。实际放置的是MyCollectionWrapper类。
	* MyDataSource实现java.sql.DataSource接口。
		1. 使用静态代码块注册驱动程序。
		2. 引用CollectionPool类，并对其进行初始化，重写getConnection方法
	* MyConnectionWrapper类，使用装饰模式，实现Collection接口。
		1. 内部引用Collection类和ConnectionPool类
		2. 重写close()方法，使其将MyCollectionWrapper连接放回连接池
* 使用第三方连接池
	* 包括C3P0,DHCP
	* C3P0的使用：
		1. 添加依赖
		2. 设置连接参数
		3. 从连接池中获取连接，操作数据库。
		4. 释放连接至连接池中。


