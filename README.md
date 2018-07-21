# Prepare
- create db according to application.properties
- create table in schema test:sql
```sql
CREATE TABLE `member` (
	`id` BIGINT(20) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`password` VARCHAR(255) NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
```
- insert some data as you like
```sql
insert into member values(2,'fjd','145')
```
![db](https://github.com/fanjingdan012/ssm/master/doc/pics/db.png)

# Run it
- Run SSMApplication.java
- visit http://localhost:8080/members/fjd
- visit http://localhost:8080/hello
- visit http://localhost:8080/actuator/health
![membersfjd](https://github.com/fanjingdan012/ssm/master/doc/pics/membersfjd.png)
