SELECT A.*
		FROM (SELECT ROWNUM AS RNUM, B.*
			FROM (
				SELECT brd_id, 
						bgr_id, 
						mem_id, 
						brd_title, 
						brd_content, 
						brd_hit, 
						regdate 
						FROM board where bgr_id = 1 
							ORDER BY REGDATE desc )B)A
			WHERE A.RNUM BETWEEN  1 AND  10
				ORDER BY A.RNUM;

					SELECT A.*
		FROM (SELECT ROWNUM AS RNUM, B.*
			FROM (
				SELECT brd_id, 
						bgr_id, 
						mem_id, 
						mem_nickname,
						brd_title, 
						brd_content, 
						brd_hit, 
						regdate
						FROM board where bgr_id = 1 
							and brd_title like '%ㅎ%'
							ORDER BY REGDATE desc )B)A
			WHERE A.RNUM BETWEEN 1 AND 10
				ORDER BY A.RNUM;
				
SELECT count(a.brd_id)
			FROM (SELECT ROWNUM AS RNUM, B.*
			FROM (SELECT brd_id, 
						bgr_id, 
						mem_id, 
						mem_nickname,
						brd_title, 
						brd_content, 
						brd_hit, 
						regdate
			FROM board
				where bgr_id = 1 and mem_nickname like '%노%'
				)B)A;
				
				
SELECT COUNT(*) FROM board where bgr_id = 1 and brd_title like '%안%';


	SELECT A.*
			FROM (SELECT ROWNUM AS RNUM, B.*
				FROM (SELECT brd_id, 
						bgr_id, 
						mem_id, 
						mem_nickname,
						brd_title, 
						brd_content, 
						brd_hit, 
						regdate
						FROM board where bgr_id = 1
								and brd_title like '%안%'
							ORDER BY REGDATE dessc )B)A
			WHERE A.RNUM BETWEEN 11 AND 20
				ORDER BY A.RNUM;
				
select * from BOARD_COMMENT;
update board_comment set cmt_content = '수정수정' and regdate = sysdate
where cmt_id = 1;

select * from board where bgr_id = 2;
select nvl(max(brd_id), 1) from board where bgr_id = 2;

select img_id, brd_id, mem_id, img_name, regdate from img_file where brd_id = 2;


select * from board_comment;
select count(*) from board_comment where brd_id = 7;
select nvl((select id from member WHERE email = 'admin@naver.co' and name = '김관리') , null) as id from dual;

select * from member;
select * from board;
update member set pwd = '1234';
update board set brd_hit = brd_hit+1 where brd_id = 1;
update member set email = 'test@teasst.com' where id = 'admin';
select * from member where id = 'admin' and pwd = '1234' and use_yn = 'y';

update member set auth_state = 0 where id = 'test2'
update member set pwd = '1234' where id = 'test2'


		SELECT A.*
			FROM (SELECT ROWNUM AS RNUM, B.*
				FROM (SELECT brd_id, 
						bgr_id, 
						mem_id, 
						mem_nickname,
						brd_title, 
						brd_content, 
						brd_hit, 
						regdate 
						FROM board where mem_id = 'test2'
							ORDER BY REGDATE desc )B)A
			WHERE A.RNUM BETWEEN 1 AND 10
				ORDER BY A.RNUM;

	

		SELECT count(a.brd_id)
			FROM (SELECT ROWNUM AS RNUM, B.*
				FROM (SELECT brd_id, 
						bgr_id, 
						mem_id, 
						mem_nickname,
						brd_title, 
						brd_content, 
						brd_hit, 
						regdate
				FROM board where mem_id = 'test2'
			)B)A;
			
select distinct b.brd_id
from board b left join board_comment c on b.brd_id = c.brd_id
where c.mem_id = 'test4'
order by b.brd_id desc;


select distinct b.brd_id, b.brd_title, b.mem_id, c.mem_id
from board_comment c 
left join board b on c.brd_id = b.brd_id 
where c.brd_id = b.brd_id and c.mem_id = 'test4'
order by b.brd_id desc;

SELECT A.*
			FROM (SELECT ROWNUM AS RNUM, B.*
				FROM (
			select distinct b.brd_id
					from board b left join board_comment c on b.brd_id = c.brd_id
					where c.mem_id = 'test4'
					order by b.brd_id desc )B)A
			WHERE A.RNUM BETWEEN 1 AND 10
				ORDER BY A.RNUM
				
SELECT count(a.brd_id)
			FROM (SELECT ROWNUM AS RNUM, B.*
				FROM (
				select distinct b.brd_id
					from board b left join board_comment c on b.brd_id = c.brd_id
					where c.mem_id = 'test4'
			)B)A

SELECT A.*    
FROM (
SELECT ROWNUM AS RNUM, B.*     
FROM (    
select distinct b.brd_id from board b left join board_comment c on b.brd_id = c.brd_id      where c.mem_id = 'test2'     order by b.brd_id desc )B)A    WHERE A.RNUM BETWEEN 1 AND 10     ORDER BY A.RNUM;

select * from member;
select count(*) from member where email = 'chini91@naver.com'