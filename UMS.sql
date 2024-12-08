create database ums;
use ums;

set sql_safe_updates=0;

-- creating tables
create table studentdata(sid int primary key auto_increment, sname varchar(25), dob date, email varchar(50), loc varchar(25));
create table studentattendance(sid int primary key auto_increment, sname varchar(25), dayspresent int default 0, totaldays int default 150, percentage int default 0);
create table studentfee(sid int primary key auto_increment, sname varchar(25), totalfee int default 10000, feepaid int default 0, feedue int default 10000);
create table studentmarks(sid int primary key auto_increment, sname varchar(25), m1 int default 0, m2 int default 0, m3 int default 0, total int default 0, percentage int default 0);
create table studentlib(sid int primary key auto_increment, sname varchar(25), bookstaken int default 0, booksreturned int default 0, bookspending int default 0);

-- insertion into studentdata table
insert into studentdata values(2300101,"Raj","2000-12-01","raj@ums.com","hyderabad");
insert into studentdata values(2300102,"Vamsi","2000-02-04","vamsi@ums.com","chennai");
insert into studentdata values(2300103,"Ravi","2000-04-02","ravi@ums.com","mumbai");
insert into studentdata values(2300104,"Surya","2001-01-01","surya@ums.com","mumbai");
insert into studentdata values(2300105,"Kiran","1999-01-12","kiran@ums.com","hyderabad");
insert into studentdata values(2300106,"Priya","2001-07-01","priya@ums.com","chennai");
insert into studentdata values(2300107,"Latha","1999-05-12","latha@ums.com","hyderabad");

-- insertion into studentattendance table
insert into studentattendance(sid,sname,dayspresent) values(2300101,"Raj",135);
insert into studentattendance(sid,sname,dayspresent) values(2300102,"Vamsi",141);
insert into studentattendance(sid,sname,dayspresent) values(2300103,"Ravi",114);
insert into studentattendance(sid,sname,dayspresent) values(2300104,"Surya",136);
insert into studentattendance(sid,sname,dayspresent) values(2300105,"Kiran",128);
insert into studentattendance(sid,sname,dayspresent) values(2300106,"Priya",109);
insert into studentattendance(sid,sname,dayspresent) values(2300107,"Latha",148);
update studentattendance set percentage=(dayspresent/totaldays)*100;

-- insertion into studentfee table
insert into studentfee(sid,sname,totalfee,feepaid) values(2300101,"Raj",10000,10000);
insert into studentfee(sid,sname,totalfee,feepaid) values(2300102,"Vamsi",10000,8000);
insert into studentfee(sid,sname,totalfee,feepaid) values(2300103,"Ravi",10000,7000);
insert into studentfee(sid,sname,totalfee,feepaid) values(2300104,"Surya",10000,5000);
insert into studentfee(sid,sname,totalfee,feepaid) values(2300105,"Kiran",10000,10000);
insert into studentfee(sid,sname,totalfee,feepaid) values(2300106,"Priya",10000,9000);
insert into studentfee(sid,sname,totalfee,feepaid) values(2300107,"Latha",10000,10000);
update studentfee set feedue=totalfee-feepaid;

-- insertion into studentlib table
insert into studentlib(sid,sname,bookstaken,booksreturned) values(2300101,"Raj",5,5);
insert into studentlib(sid,sname,bookstaken,booksreturned) values(2300102,"Vamsi",1,0);
insert into studentlib(sid,sname,bookstaken,booksreturned) values(2300103,"Ravi",2,0);
insert into studentlib(sid,sname,bookstaken,booksreturned) values(2300104,"Surya",6,2);
insert into studentlib(sid,sname,bookstaken,booksreturned) values(2300105,"Kiran",1,1);
insert into studentlib(sid,sname,bookstaken,booksreturned) values(2300106,"Priya",3,2);
insert into studentlib(sid,sname,bookstaken,booksreturned) values(2300107,"Latha",3,2);
update studentlib set bookspending=bookstaken-booksreturned;

-- insertion into studentmarks table
insert into studentmarks(sid,sname,m1,m2,m3) values(2300101,"Raj",70,89,91);
insert into studentmarks(sid,sname,m1,m2,m3) values(2300102,"Vamsi",77,69,81);
insert into studentmarks(sid,sname,m1,m2,m3) values(2300103,"Ravi",78,59,94);
insert into studentmarks(sid,sname,m1,m2,m3) values(2300104,"Surya",67,69,71);
insert into studentmarks(sid,sname,m1,m2,m3) values(2300105,"Kiran",70,80,70);
insert into studentmarks(sid,sname,m1,m2,m3) values(2300106,"Priya",87,79,77);
insert into studentmarks(sid,sname,m1,m2,m3) values(2300107,"Latha",70,81,73);
update studentmarks set total=m1+m2+m3;
update studentmarks set percentage=total/3;


-- creating facultymain table
create table facultymain(fid int primary key auto_increment, fname varchar(25), fpwd varchar(25) default "fpass@123");

-- insertion into facultymain table
insert into facultymain(fid,fname) values(2355101,"Ramesh");
insert into facultymain(fname) values("Vikram");
insert into facultymain(fname) values("Rahul");


-- creating studentmain table
create table studentmain(
sid int primary key auto_increment,
sname varchar(25),
spwd varchar(25) default "spass@123",
foreign key (sid) references studentdata(sid),
foreign key (sid) references studentattendance(sid),
foreign key (sid) references studentfee(sid),
foreign key (sid) references studentmarks(sid),
foreign key (sid) references studentlib(sid)
);

-- insertion into studentmain table
insert into studentmain(sid,sname) values(2300101,"Raj");
insert into studentmain(sname) values("Vamsi");
insert into studentmain(sname) values("Ravi");
insert into studentmain(sname) values("Surya");
insert into studentmain(sname) values("Kiran");
insert into studentmain(sname) values("Priya");
insert into studentmain(sname) values("Latha");