-- call next value for hibernate_sequence;
insert into MEMBER(`name`,`email`,`created_at`,`updated_at`) values ('khs','khs@naver.com',now(),now());

-- call next value for hibernate_sequence;
insert into member(`name`,`email`,`created_at`,`updated_at`) values ('khs2','khs6@naver.com',now(),now());

-- call next value for hibernate_sequence;
insert into member(`name`,`email`,`created_at`,`updated_at`) values ('khs3','khs3@naver.com',now(),now());

-- call next value for hibernate_sequence;
insert into member(`name`,`email`,`created_at`,`updated_at`) values ('khs4','khs4@naver.com',now(),now());

-- call next value for hibernate_sequence;
insert into member(`name`,`email`,`created_at`,`updated_at`) values ('khs','khs5@naver.com',now(),now());


insert into publisher(id,name) values(1,'패스트 캠퍼스');
insert into book(id,name,publisher_id,deleted) values (1,'JPA 패키지',1,false);
insert into book(id,name,publisher_id,deleted) values (2,'JPA 패키지 2편',1,false);
insert into book(id,name,publisher_id,deleted) values (3,'JPA 패키지 3편',1,true);
