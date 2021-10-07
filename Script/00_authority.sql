drop table authorities;
select * from authorities order by authority;


-- username은 member id
create table authorities (
      username varchar2(50) not null,
      authority varchar2(50) not null
);
ALTER TABLE authorities ADD CONSTRAINT 
	FK_ID_AUTH FOREIGN KEY(username) REFERENCES member(id);
	


insert into authorities (username, authority) values ('admin','ROLE_ADMIN');
insert into authorities (username, authority) values ('mng','ROLE_MANAGER');

insert into authorities (username, authority) values ('test00','ROLE_USER');
insert into authorities (username, authority) values ('test11','ROLE_USER');