# ATM_PM_II

create database BANK;
use BANK;
create table Customer(
    customerid int not null,
    customername varchar(35),
    PIN int
);

alter table Customer
add constraint pka primary key(customerid); 

create table Account(
   customerid int not null,
   accounttype varchar(15),
   balance numeric
);
alter table Account add  column PIN int references Customer(PIN);
alter table Account
add constraint fka foreign key(customerid) references Customer(customerid);

insert into Customer values(1,"Ishaan",1062);
insert into Account values(1,"Savings",50000,1062);
insert into Customer values(2,"Soham",1616);
insert into Account values(2,"Savings",50000,1616);
insert into Customer values(3,"Sarthak",1514);
insert into Account values(3,"Savings",50000,1514);
insert into Customer values(4,"Vivek",1489);
insert into Account values(4,"Savings",1895,1489);
********************************************************************************
The 4 customers already added to the database are:
1. Ishaan:
   Customer ID=1;
   PIN=1062;
   Balance=50000;
2. Soham;
   Customer ID=2;
   PIN=1616;
   Balance=50000;
3. Sarthak;
   Customer ID=3;
   PIN=1514;
   Balance=50000;
4. Vivek;
   CustomerID=4;
   PIN=1489;
   Balance=50000;
   
 **********************************************************************************
 
 To run the application, run the main function in ATM.java, login with given credentials and perform any activities you wish limited by the options in the Menu.
