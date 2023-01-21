-- root에서 실시
-- database 생성
create database gradle default character set utf8mb4;

-- user 생성
create user 'gradle'@'%' identified by '1234';
create user 'gradle'@'localhost' identified by '1234';

-- 권한 부여
-- % : 모든 대상
-- localhost : 해당 ip
grant all privileges on gradle.* to 'gradle'@'%';
grant all privileges on gradle.* to 'gradle'@'localhost';

-- 새 변경사항 적용
flush privileges;

-- ----------------------------

-- gradle db에 좌측 스키마 선택 후 실시 
show databases;

desc member;

select * from member;