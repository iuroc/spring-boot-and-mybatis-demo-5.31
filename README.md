## 功能

- 后端部分
    - 部门增删改查
    - 员工增删改查
- 前端部分
    - 部门管理
    - 员工管理

## 数据库结构

### 部门

```sql
CREATE TABLE IF NOT EXISTS `dept` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
)
```

### 员工

```sql
CREATE TABLE IF NOT EXISTS `emp` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT '123456' COMMENT '密码',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 说明: 1 男, 2 女',
  `image` varchar(300) DEFAULT NULL COMMENT '图像',
  `job` tinyint unsigned DEFAULT NULL COMMENT '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
  `entry_time` date DEFAULT NULL COMMENT '入职时间',
  `dept_id` int unsigned DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
)
```