CREATE database RevP2;
use RevP2;
create table AuthTable(userid varchar(15) NOT NULL, password varchar(15) NOT NULL, AdminFlag Boolean default false);
insert into AuthTable VALUES("Admin","Password",true);
SELECT * FROM revp2.authtable;