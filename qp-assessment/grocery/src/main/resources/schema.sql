drop sequence grocery_seq if exists;
drop sequence order_seq if exists;
drop sequence order_item_seq if exists;
drop table grocery if exists;
drop table item_quantity if exists;
drop table user_details if exists;
drop table order_item_details if exists;
drop table order_details if exists;
create sequence grocery_seq;
create sequence order_seq;
create sequence order_item_seq;
create table grocery(
grocery_Id Long primary key,
grocery_Name VARCHAR NOT NULL,
price Double NOT NULL
);


create table item_quantity(
grocery_Id Long ,
quantity Long NOT NUll DEFAULT 0);

create table order_details(
order_id Long,
order_placed date,
user_id Long
);

create table order_item_details(
id Long,
grocery_Id Long,
order_id Long,
quantity Long
);

create table user_details(
user_id LONG NOT NULL,
user_name varchar(50),
role varchar(50),
password varchar(50)
);

insert into user_details values(1,'user1','ROLE_USER','user1');
insert into user_details values(2,'admin','ROLE_ADMIN','admin');
