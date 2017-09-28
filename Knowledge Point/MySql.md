 # MySql
* RDBMS 关系型数据库
## 关系型数据库 RDBMS
* 二维表格数据管理系统，存放结构化数据
1. Table 表
2. column field（字段）
3. row record（记录）
4. 库 表的集合
### 产品
* MySql，Oracle，DB2，Sql Server
* 遵从SQL（结构化查询语言）
## 安装MySql
0. 卸载之前安装的MySql
1. 下载MySql
2. 用管理员身份安装，避免中文和空格目录
3. 自定义（custom)安装---指定安装目录---Ik-----next
4. 点击finish，启动配置向导
5. 一直next-----
6. 设置networking options，打钩（add firewall。。。）
7. 选择默认的字符集（选择第三个单选按钮-手动选择默认字符集，字符集使用utf8）
8. 设置root账户密码，同时选中允许remote login
9. next----
10. finish
11. 验证安装
	1. 查看mysql服务是否启动
	2. 进入mysql的命令行程序，需输入密码
## sc命令
* service console，服务控制台程序，可以启动，停止，删除等服务
* 规范，都是接口。
````
1. mysql -h IP -u user -p 	//远程连接MySql
2. mysql -u user -p 		//连接本地MySql
3. mysql --help			//mysql命令帮助
4. mysql>select current_date ;	//查看当天日期
5. mysql>select now() ;		//查看时间
6. mysql>-- 注释
7. mysql>show databases	;	//显示所有数据库
8. mysql>drop database name ;
9. mysql>create database name ;
10. mysql>use name ;		//指定使用数据库
11. mysql>show tables;		//显示所有表
11. mysql>create table name(id int,name varchar(20),age int)；
12. mysql>describe/desc tablename ;	//查看表结构
13. mysql>drop table tablename ;	
14. mysql>select filed1,filed2 form tablename where ;	//is null/is not null	
15. mysql>insert into tablename(filed) values(value);
16. mysql>update tablename set filed1=value1,filed2=value2 where ;
17. mysql>delete from test where ;	//删除记录
18. mysql>source path			//脚本执行
````
* 修改表 
````
-- 为表添加列
alter table tablename add column name type
-- 修改表名
alter table oldname rename newname;
-- 修改某个字段不能为空
alter table tablename modify columnname not null;
-- 修改表的主键为自增
alter table tablename modify id int unsigned auto_increment；
-- 修改表中某个字段
alter table tablename change oldColumnName newColumnName type;
````	
## 字段类型
1. tinyint	//1 byte
2. smallint //3 short
3. mediumint // 3
4. int // 4 int
5. big int // 8 long
6. char(20) //定长
7. varchar（255) // 动态
8. blob // binary large object，二进制大对象 255  
9. longblob //4GB
10. text // 255
11. long text //4GB
12. 大字段处理：使用文件流处理大文件，字节数组处理小文件。
## 存储过程
1. store procedure，存在数据库的一组SQL语句，在服务器端执行。
2. 创建存储过程：
* 默认in。
````
mysql> delimiter //
mysql> create procedure sp_biginsert(in num int)
    -> begin
    -> declare i int default 0;
    -> start transaction;
    -> while i < num do
    ->  insert into persons(name,password,age) values(concat('tom',i),concat('',i),i % 30);
    ->  set i = i+1;
    -> end while
    -> commit;
    -> end
    -> //
````
3. 赋值语句
	1. set = 
	2. into
4. declare i int default 0；
5. 循环
````
while 条件 do
	command;
end while;
````
6. concat()；连接函数
6. show procedure status 显示存储过程列表
## 自定义函数
1. 创建函数
````
mysql> create Function f_add (a int, b int) returns int
    -> return a + b //
````
2. java调用函数，与存储过程类似

## 事务
1. Transaction 一组不可分割的操作。
2. 特点
	1. a：Atomic。原子性，数据不可分割
	2. c：Consistent。一致性，数据不被破坏
	3. i：Isolate。隔离性，各个事务之间相互独立
	4. d：Durable。永久性，数据永久保存。
3. 操作
	1. commit()；提交，默认开启自动提交
	2. rollback()；回滚
	3. 以上都属于标记性操作，不影响数据的位置
4. 客户端控制事务
	1. 关闭自动提交。````set autocommit = 0;````
	2. 开启事务。````start transaction;````
	2. 操作。增删改查
	3. 提交/回滚。````commit;/rollback;
### 事务的并发
1. 导致3个现象。
	1. 脏读（读脏）：读未提交。（一个事务读到另一个事务已修改未提交的数据，而另一个事务rollback修改操作。
	2. 不可重复读：一个事务在进行相同条件查询，连续两次，结果不同。（更新）
	3. 幻读（虚读）：一个事务在进行相同条件的查询，连续两次，后一次查询的结果中发现一些原来没有的记录。（插入）
2. 隔离级别。作用：避免出现哪种事务并发现象
	1. 1-read uncommitted 读未提交
	2. 2-read committed 读已提交
	3. 4-repeatable read 可以重复读
	4. 8-Serializable 串行化
3. 设置隔离级别（事务开启之前）
* ````set [global | session] transation level [1|2|4|8]````
4. 查看事务隔离级别
	1. ````select @@global.tx_isolation````，查看全局隔离级别
	2. ````select @@session.tx_isolation````，查询当前会话隔离级别
	3. ````select @@tx.isolation_isolation````，查询当前会话隔离级别
4. MySQL默认````4-repeatable read````,同时避免不可重复读和幻读问题，支持4个隔离级别。
5. Oracle：默认开启````2-read committed````。
## 行级锁和表级锁
* 行级锁（row block）：当多个事务并发修改同一条记录时，只有一个事务可以操作，其它事务阻塞。
* 表级锁：
	* ````lock tables tablename writer/read````
	* ````unlock tables````
	* 性能较差，存储过程使用方便

## 索引
* 索引就是一个指针，指向表里的数据。
* 索引通常以一种树形结构保存信息，因此速度较快。
## 索引类型
### 单字段索引
* 某个字段经常在WHERE字句中作为单独查询条件，它的但字段索引是最有效的。
* 适合作为单字段索引的值有个人标示号码，序列号或系统指派键值
### 唯一索引
* 唯一索引用于改善性能和保证数据完整性。唯一索引不允许表里有重复值
* 在创建数据库结构的同时，基于空白表来创建索引。确保后续输入的数据完全满足用户的要求。
* 在既有数据中创建索引，就必须对数据进行相应分析工作。
### 组合索引
* 组合索引是基于一个表里两个或多个字段的索引。
* 字段在索引里的次序对对数据检索速度有很大影响。一般，最具限制的值应该排在前面，从而得到最好的性能。
* 考虑WHERE字句里经常使用什么字段。如果使用一个字段，单字段索引是适合的；如果使用两个或多个字段，组合索引就是最好的索引。
### 隐含索引
* 隐含索引是数据库服务程序在创建对象时自动创建的。
* 在创建主键或唯一性约束时，数据库自动为它们创建索引。
## MySql搜索引擎
### MyISAM
* ISAM执行读取操作速度快，不占大量内存和存储资源。缺点在于不支持事务处理，也不能容错。
* MyISAM是MySql的ISAM扩展格式和缺省的数据库格式引擎。不支持数据事务、行级锁和外键。当进行插入或更新数据时需要锁定整个表，效率低。MyISAM强调快速读取操作。
### HEAP
* HEAP允许只驻留在内存里的临时表格。驻留的内存令操作最快。
* 管理数据不稳定，关机易丢失
* HEAP表格在需要使用SELECT表达式来选择和控制数据的时候非常有用
### innoDB和BerkleyDB
* InnoDB和BDB包括了对事务的处理和外来键支持
### MyISAM和InnoDB的区别：
1. 文件存储
	1. InnoDB在磁盘上存储表空间数据文件和日志文件
	2. MyISAM在磁盘上存储三个文件，命名类型：`表名.文件类型`。
		* `.frm`，存储表定义
		* `MYD`，数据文件扩展名
		* `MYI`，索引文件扩展名
2. InnoDB支持事务，外部键等高级功能；MyISAM强调性能，执行速度快，但不支持事务
3. 对数据的大量INSERT和UPDATE，使用InnoDB性能更佳；执行大量SELECT，MyISAM更佳。
4. 表的具体行数
	1. `select count(*) from table`，MyISAM只简单读出保存好的行数。注意的是，当count（*）包含where语句时，两种表的操作是一样的。
	2. InnoDB中不保存具体行数，也就是说，执行`select count(*) from table`时，InnoDB要扫描一遍整个表来计算有多少行。
5. 锁
	1. MyISAM仅支持表锁
	2. InnoDB提供行锁。提供与Oracle类型一致的不加锁读取；如果执行一个SQL语句时，MySql不确定要扫描的范围，InnoDB同样会锁全表。
4. InnoDB不支持全文索引，而MyISAM支持。
5. InnoDB可以通过重播其日志从崩溃或其它意外关闭恢复。MyISAM
## 性能调优
### in和exists。
1. in()语句，只执行一次，它查出表中所有的字段值并缓存起来，之后再检查字段值是否与结果值相符。
2. exists语句，并不缓存结果集，重点查看结果集中是否有记录，类似于遍历查询集。
3. 在多表查询中，若结果集表数据量较大时，使用exists；若查询集较大时使用in；若一样大，则二者效率差不多。 


