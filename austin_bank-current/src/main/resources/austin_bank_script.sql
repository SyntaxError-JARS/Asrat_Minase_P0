--Database for Austin Bank Application

-- Creating a schema for Banking Application

create schema banking_application_asrat_minase_P0;

--Creating a Coustmer table with its attributes.

create table customer (

	First_Name varchar(50) not null,
	Last_Name varchar (50) not null,
	DOB date not null,
	SSN varchar(20) primary key,
	Username_U varchar(50) not null unique,
	Password_P varchar(50) not null,
	Email varchar(254)

);

--Creating a Account table with its attributes.

create table account (

	CustId serial primary key,
	Balance numeric check(Balance > 0 and Balance < 250000),
	Account_Number varchar (50) not null unique ,
	SSN varchar(20),
	Accout_Type varchar(100),
	Registration_Date date not null

);

alter table account
add constraint  fk_SSN
foreign key(SSN) references customer(SSN);

--Creating a Account table with its attributes.

create table transaction_History (

	CustId serial primary key,
	Tran_Date date not null,
	Amount numeric not null check (Amount > 0 and Amount < 250000),
	Account_Number varchar (50) not null unique
);

--Creating a foreign key on fk_Account_Number which relate account table with transaction_History table

alter table account
add constraint  fk_Account_Number
foreign key(Account_Number) references transaction_History(Account_Number);

--Inserting Data to customer table

insert into customer (First_Name, Last_Name, DOB, SSN, Username_U, Password_P, Email)
values ('Asrat','Minase','01/01/1900','888-00-1212','aminase','whrw@hgth','asrat.minase12@gmail.com');

insert into customer (First_Name, Last_Name, DOB, SSN, Username_U, Password_P, Email)
values ('John','Doe','01/06/1980','855-00-0000','jdoe','ddd@676','john.doe@gmail.com');

insert into customer (First_Name, Last_Name, DOB, SSN, Username_U, Password_P, Email)
values ('Robert','Tyler','11/01/2000','444-00-0000','rTyler','rrrw@h67h','asrat.minase12@gmail.com');


--Inserting Data to account table

insert into account
values (default, 94000.00, '295056','888-00-1212','Saving', '01-01-2021');

insert into account
values (default, 4567.00, '286111','888-00-1212','Cheking', '01/01/2022');

insert into account
values (default, 7845.00, '455244', '444-00-0000', 'Saving', '11/05/2021');

insert into account
values (default, 34335.00, '249056', '855-00-0000', 'Saving', '10/05/2021');

insert into account
values (default, 56555.00, '269808', '855-00-0000', 'Checking', '10/05/2021');


--Inserting Data to transaction_History table

insert into transaction_History
values (default, '01-02-2022', 1000.00, '299056')

insert into transaction_History
values (default, '07-01-2022', 200.00, '295056')

insert into transaction_History
values (default, '03-04-2022', 500.40, '286111')

insert into transaction_History
values (default, '01-01-2022', 1000.50, '455244')

insert into transaction_History
values (default, '03-04-2022', 4500.70, '249056')

insert into transaction_History
values (default, '03-04-2022', 400.00, '219056')

insert into transaction_History
values (default, '03-04-2022', 780.00, '269798')

insert into transaction_History
values (default, '03-04-2022', 100.00, '269808')

