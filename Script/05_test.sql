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