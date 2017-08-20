# SQL
## MySql约束
1. primary key 主键 
	1. 不为null，不重复
	2. create table tablename(filed type primary key);
2. 自增
	1. create table name (filed type auto_imcrement);
3. 带条件创建
	1. create database if not exits name;
	2. create table if not exists tablename;
	3. drop database if exists name;
4. 查询
	1. 排序
		* order by filed desc/aesc 降/升序排列，可组合多个字段
	2. 聚焦函数（统计）
		1. count() 计数
		2. max()
		3. min()
		4. avg()
		5. sum()
	3. 模糊查询
		1. ````%````,0个或多个字符
		2. ````_````,一个字符，作字符内容使用时，应使用转义符
	4. 分页查询
		1. limit 偏移量，个数