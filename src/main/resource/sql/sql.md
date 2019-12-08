创建数据库用户表：
```SQL
  CREATE TABLE `user` (
  	`login_id` VARCHAR ( 20 ) NOT NULL COMMENT '登陆ID',
  	`pwd` VARCHAR ( 20 ) NOT NULL COMMENT '用户密码',
  	`name` VARCHAR ( 100 ) NOT NULL COMMENT '用户姓名',
  	`age` INT ( 3 ) NOT NULL COMMENT '用户年龄',
  	`sex` VARCHAR ( 3 ) NOT NULL COMMENT '性别',
  	`duty` VARCHAR ( 15 ) COMMENT '职务',
  	`cell_number` VARCHAR ( 15 ) COMMENT '手机号',
  	`photo_url` VARCHAR ( 75 ) COMMENT '头像地址',
  	`used` boolean NOT NULL COMMENT '账号是否可用',
  	PRIMARY KEY ( `login_id` ) 
  ) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '用户表';
```
插入默认数据： 
```SQL
  INSERT INTO `user` ( `login_id`, `pwd`, `name`, `age`, `sex`, `used` )
  VALUES
  	( 'pc859107393', '123456', '阿程', 20, '男', TRUE ),
  	( 'pc228568859', '123456', 'chengcheng', 20, '女', TRUE ),
  	( 'pangpang', '123456', '余下一整夜', 25, '男', TRUE ),
  	( '111111', '123456', '手拉手系列教程', 22, '女', TRUE );

```

