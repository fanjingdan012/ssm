# Prepare
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
# Run it
- Run SSMApplication.java
```
curl --request GET \
  --url http://localhost:8080//members/shijie2 \
  --header 'cache-control: no-cache' \
  --header 'postman-token: 25dff487-3639-2c2b-a00a-2598b7b9281b' \
  --header 'x-tenantid: tenant2'
```
will read from test2 db

```
curl --request GET \
  --url http://localhost:8080//members/shijie \
  --header 'cache-control: no-cache' \
  --header 'postman-token: 25dff487-3639-2c2b-a00a-2598b7b9281b' \
  --header 'x-tenantid: tenant1'
```
will read from test db

# Code
See `MultitenantDataSource.java` extended from `AbstractRoutingDataSource`, calling determineCurrentLookupKey() to determin DataSource dynamicly