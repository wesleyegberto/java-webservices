CREATE SCHEMA USER_REALM_DB;
USE USER_REALM_DB;

-- Tables
CREATE TABLE Users
(
	username VARCHAR(255) PRIMARY KEY,
	passwd VARCHAR(255)
);

CREATE TABLE UserRoles
(
	username VARCHAR(255),
	role VARCHAR(32)
);


-- Default data
INSERT INTO Users (`username`, `passwd`) VALUES ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');
INSERT INTO UserRoles (`username`, `role`) VALUES ('admin', 'admin');
INSERT INTO UserRoles (`username`, `role`) VALUES ('admin', 'customer');


select *
from Users u
inner join UserRoles ur on u.username = ur.username;