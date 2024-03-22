create user ohgiraffers@'%' identified by 'ohgiraffers';

create database employee;

grant all privileges on employee.* to ohgiraffers@'%';

show grants for ohgiraffers@'%';