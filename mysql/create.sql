CREATE DATABASE if not exists campus DEFAULT CHARACTER SET utf8;

CREATE TABLE if not exists user_info 
(
id		varchar(255) NOT NULL,
pwd		varchar(255),
nickname	varchar(255),
primary	key	(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE if not exists entrust_type
(
type_id		int(2) NOT NULL auto_increment,
content		varchar(255),
primary key	(type_id)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE if not exists entrust
(
en_id		int(6) NOT NULL auto_increment,
from_id		varchar(255),
time		varchar(255),
place		varchar(255),
type		int(2),
event		varchar(255),

primary key	(en_id),
foreign key (from_id) references user_info(id)
		on delete set null,
foreign key (type) references entrust_type(type_id)
		on delete set null
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE if not exists reply
(
re_id		int(6) NOT NULL auto_increment,
entrust_id	int(6),
from_id		varchar(255),
content		varchar(255),

primary key	(re_id),
foreign key (from_id) references user_info(id)
		on delete set null
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;