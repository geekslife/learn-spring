create table users (
  id varchar(10) PRIMARY KEY,
  name VARCHAR (20) NOT NULL,
  password VARCHAR(10) NOT NULL
)

alter table users add Level tinyint not null;
alter table users add Login int not null;
alter table users add Recommend int not null;

