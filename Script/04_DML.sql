
/* USER */
select * from member where id = 'test' and pwd = '1234';
select id ,pwd, name, nickname, email, auth_key, auth_state, use_yn, regdate from member order by regdate;
-----사용자------
insert into member values ('test', '1234', '김이름', '노인증', 'test00@naver.com', '123455', '0', 'y', sysdate);
--인증안한사람

insert into member values ('test11', '1234', '김이름', '인증닉', 'test22@naver.com', '123455', '1', 'y', sysdate);
--인증한사람


------관리자------
insert into member values ('admin', '1234', '김관리', '관리자', 'admin@naver.com', '123455', '1', 'y', sysdate);
insert into member values ('mng', '1234', '김매니저', '매니저', 'mng@naver.com', '123455', '1', 'y', sysdate);


/* 게시판 종류 */
select * from BOARD_GROUP;
insert into board_group(bgr_id, bgr_key, bgr_name) values (brd_group_seq.nextval, 'gnr', '일반');
insert into board_group(bgr_id, bgr_key, bgr_name) values (brd_group_seq.nextval, 'img', '이미지');

/* 일반 test글 */
select  brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit, regdate from board;
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content) values ( brd_seq.nextval, 1,'test', '노인증', '제목', '내용');
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test', '닉일', '안녕하세용1', '내용1', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test', '닉이', '안녕하세용2', '내용2', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test11', '닉삼','안녕하세용3', '내용3', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 2, 'test11', '닉사','안녕하세용4', '내용4', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 2, 'test11', '닉오','안녕하세용5', '내용5', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test', '닉육','안녕하세용1', '내용1', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test', '닉ㅊ칠','안녕하세용2', '내용2', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test11', '닉팔','안녕하세용3', '내용3', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 2, 'test11', '닉구','안녕하세용4', '내용4', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 2, 'test11', '닉십일','안녕하세용5', '내용5', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test', '닉십이','안녕하세용1', '내용1', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test', '닉십삼','안녕하세용2', '내용2', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 1, 'test11', '십사','안녕하세용3', '내용3', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 2, 'test11', '십오','안녕하세용4', '내용4', 0);
insert into board(brd_id, bgr_id, mem_id, mem_nickname, brd_title, brd_content, brd_hit) values (brd_seq.nextval, 2, 'test11', '십육','안녕하세용5', '내용5', 0);
	
/* 게시판 카테 */
select brd_cate_id, board_group_id, brd_cate_key, brd_cate_name, brd_parent, brd_cate_order from board_cate;
insert into board_cate values (brd_cate_seq.nextval, 2, 'seoul', '서울', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'busan', '부산', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'daegu', '대구', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'incheon', '인천', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'gwangju', '광주', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'daejeon', '대전', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'ulsan', '울산', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'sejong', '세종', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'gyeonggi-do', '경기', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'gangwon-do', '강원', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'chungcheongbuk-do', '충북', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'chungcheongnam-do', '충남', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'jeollabuk-do', '전북', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'jeollanam-do', '전남', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'gyeongsangbuk-do', '경북', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'gyeongsangnam-do', '경남', 0, 0);
insert into board_cate values (brd_cate_seq.nextval, 2, 'jeju-do', '제주', 0, 0);


/* 댓글 */
select cmt_id, brd_id, mem_id, cmt_lv, cmt_seq, cmt_content, regdate from board_comment where brd_id = 74;
insert into board_comment(cmt_id, brd_id, mem_id, cmt_content) values (brd_cmt_seq.nextval, 74, 'test', '74번글의 댓글');
insert into board_comment(cmt_id, brd_id, mem_id, cmt_content) values (brd_cmt_seq.nextval, 74, 'test', '74번글의 댓글2');


select img_id, brd_id, mem_id, img_name, img_path, regdate from img_file;