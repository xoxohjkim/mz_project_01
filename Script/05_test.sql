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
							ORDER BY REGDATE desc )B)A
			WHERE A.RNUM BETWEEN 11 AND 20
				ORDER BY A.RNUM;