--Database for Austin Bank Application

-- Creating a schema for Banking Application

create schema banking_application_asrat_minase_P0;

--Creating a Coustmer table with its attributes.

create table customer (

	first_Name varchar(50) not null,
	last_Name varchar (50) not null,
	DOB varchar not null,
	SSN varchar(20) primary key,
	username_U varchar(50) not null unique,
	password_P varchar(50) not null,
	email varchar(254)

);

--Creating a Account table with its attributes.

create table account (

	custId serial primary key,
	balance numeric check(balance > 0 and balance < 250000) GENERATED ALWAYS as(deposit-withdrawal) stored,
	account_Number varchar (50) not null unique ,
	account_type varchar(50) not null,
	deposit numeric check(deposit >= 0) ,
	withdrawal numeric check(withdrawal >= 0) ,
	registration_date varchar (30),
	SSN varchar(20) not null
);


alter table account
add constraint  fk_SSN
foreign key(SSN) references customer(SSN)
on delete cascade;

--Inserting Data to customer table

insert into customer (First_Name, Last_Name, DOB, SSN, Username_U, Password_P, Email)
values ('Asrat','Minase','01/01/1900','888-00-1212','aminase','whrw@hgth','asrat.minase12@gmail.com');

insert into customer (First_Name, Last_Name, DOB, SSN, Username_U, Password_P, Email)
values ('John','Doe','01/06/1980','855-00-0000','jdoe','ddd@676','john.doe@gmail.com');

insert into customer (First_Name, Last_Name, DOB, SSN, Username_U, Password_P, Email)
values ('Robert','Tyler','11/01/2000','444-00-0000','rTyler','rrrw@h67h','asrat.minase12@gmail.com');


--Inserting Data to account table

insert into account
values (default, default,'123123','Saving',1000.0, 100.0, '01-20-2022', '888-00-1212');
insert into account
values (default, default,'123344','Checking',500.0, 400.0, '01-2-2022', '855-00-0000');
insert into account
values (default, default,'1234323','Saving',800, 100.0, '01-20-2222', '444-00-0000');
