create database CafeLS;
use CafeLS;
create table users (  
   id varchar(20) not null,
   pwd varchar(20) not null,
   name varchar(20) not null,
   gender varchar(6) not null,
   birth varchar(20) not null,
   tel varchar(20) not null,
   addr varchar(30) not null,
   primary key(id) 
);

create table items (
   item_code int not null AUTO_INCREMENT,
   name varchar(20) not null,
   quantity int not null,
   price int not null,
   primary key(item_code) 
);

create table orders (
   order_number int not null AUTO_INCREMENT,
   item_code int not null,
   quantity int not null,
   id varchar(20) not null,
   time TIMESTAMP DEFAULT NOW(),
   primary key(order_number),
   foreign key(item_code) references items(item_code),
   foreign key(id) references users(id) 
);

insert into items (name, quantity, price) value ('�Ƹ޸�ī�� ICE', 50, 3000);
insert into items (name, quantity, price) value ('ī��� ICE', 50, 4000);
insert into items (name, quantity, price) value ('��ü��', 50, 4000);
insert into items (name, quantity, price) value ('������ ����Ǫġ��', 50, 5000);
insert into items (name, quantity, price) value ('�Ƹ޸�ī�� HOT', 50, 3000);
insert into items (name, quantity, price) value ('ī��� HOT', 50, 4000);
insert into items (name, quantity, price) value ('ī���ī', 50, 4000);
insert into items (name, quantity, price) value ('���۹�Ʈ��', 50, 3500);

insert into users values ('admin2','admin','������','��','1993/05/08','010-3258-0508','������');
