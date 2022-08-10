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
insert into book(id,name,publisher_id,deleted,status) values (1,'JPA 패키지',1,false,100);
insert into book(id,name,publisher_id,deleted,status) values (2,'JPA 패키지 2편',1,false,200);
insert into book(id,name,publisher_id,deleted,status) values (3,'JPA 패키지 3편',1,true,100);

insert into review(id,title,content,score,member_id,book_id) values (1,"내 인생을 바꾸려면 읽어라","비밀",5.0,1,1);
insert into review(id,title,content,score,member_id,book_id) values (2,"내 인생을 망함","후...",3.0,2,2);

insert into comment(id,comment,review_id) values (1,"동감.",1);
insert into comment(id,comment,review_id) values (2,"저는 별로였음",1);
insert into comment(id,comment,review_id) values (3,"저도 별로였음",2);