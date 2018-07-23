# Prepare
- change db password in application.properties
- create schemas test(default and tenant1) and test2(tenant2)
- create table in 2 dbs:sql
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
insert into member values(1,'fjd','145')
insert into member values(2,'fjd','145')
```
![db-multi-datasource](https://github.com/fanjingdan012/ssm/blob/multi-tenant/doc/pics/db-multi-datasource.png)

# Run it
- Run SSMApplication.java
- visit http://localhost:8080/hello
- visit http://localhost:8080/actuator/health
- visit http://localhost:8080//members/fjd with headers
- visit http://localhost:8080/api/tenant/register?username=root&password=123456&url=jdbc:mysql://localhost/test2&tenantName=tenant2
```
curl -X GET \
  'http://localhost:8080/api/tenant/register?username=root&password=123456&url=jdbc:mysql://localhost/test2&tenantName=tenant2' \
  -H 'Cache-Control: no-cache'
```
will register a new tenant(new datasource)
```
curl --request GET \
  --url http://localhost:8080/members/fjd \
  --header 'cache-control: no-cache' \
  --header 'x-tenantid: tenant1'
```
will read from test db

```
curl --request GET \
  --url http://localhost:8080//members/shijie \
  --header 'cache-control: no-cache' \
  --header 'x-tenantid: tenant2'
```
will read from test2 db
![membersfjd-multi-tenant](https://github.com/fanjingdan012/ssm/blob/multi-tenant/doc/pics/membersfjd-multitenant.png)

# Core code
- `MultitenantConfiguration.java`
- `MultitenantDataSource.java` (`extends AbstractRoutingDataSource`)
- `MultitenantDataSourceRegister` (@Imported by MultitenantConfiguration)

# Other versions
- see other branches