
## Instruction ##


### Setup JDBC Realm ###

#### Tables ####

´´´
CREATE TABLE Users(username VARCHAR(255) PRIMARY KEY, passwd VARCHAR(255))
CREATE TABLE UserRoles(username VARCHAR(255), role VARCHAR(32)) 
´´´



#### Data ####

User: admin
Password: admin

´´´
INSERT INTO Users (`username`, `passwd`) VALUES ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');
INSERT INTO UserRoles (`username`, `role`) VALUES ('admin', 'ADMIN');
´´´



#### Security Domain in WildFly ####

´´´
<security-domain name="GeneralRealm" cache-type="default">
	<authentication>
		<login-module code="Database" flag="required">
			<module-option name="dsJndiName" value="java:/jdbc/UserRealmDS"/>
			<module-option name="principalsQuery" value="select passwd from Users where username=?"/>
			<module-option name="rolesQuery" value="select role, 'Roles' from UserRoles where username=?"/>
			<module-option name="hashAlgorithm" value="SHA-256"/>
			<module-option name="hashEncoding" value="base64"/>
		</login-module>
	</authentication>
</security-domain>
´´´


### Setup DD ###





[Example](http://blog.eisele.net/2015/01/jdbc-realm-wildfly820-primefaces51.html)
