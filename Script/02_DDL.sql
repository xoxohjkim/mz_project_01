DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE board_group CASCADE CONSTRAINTS;
DROP TABLE board_cate CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE board_comment CASCADE CONSTRAINTS;
DROP TABLE img_file CASCADE CONSTRAINTS;


/* 회원 */
create table member (
	id varchar2(20) NOT NULL, /* 아이디 */
	pwd varchar2(255) NOT NULL, /* 비밀번호 */
	name varchar(36) NOT NULL, /* 이름 */
	nickname varchar(36) NOT NULL, /* 닉네임 */
	email varchar(36) NOT NULL, /* 이메일 */
	auth_key NUMBER(10) NOT NULL, /* 인증키 */
	auth_state char(1) DEFAULT '0' NOT NULL, /* 인증상태 */
	use_yn char(1) DEFAULT 'y' NOT NULL, /* 회원탈퇴유무 */
	regdate date DEFAULT sysdate NOT NULL /* 가입일 */
)SEGMENT CREATION IMMEDIATE;

ALTER TABLE MEMBER ADD CONSTRAINT PK_MEMBER PRIMARY KEY (id);
ALTER TABLE MEMBER ADD constraint uk_member_email unique (EMAIL);
ALTER TABLE MEMBER ADD constraint uk_member_nick unique (nickname);

/* 게시판 종류 */
create table board_group (
	bgr_id NUMBER(12) NOT NULL, /* PK */
	bgr_key varchar2(20) NOT NULL, /* 게시판 key값 -> 파라미터에 씀*/
	bgr_name varchar2(20) NOT NULL /* 게시판 이름 */
)SEGMENT CREATION IMMEDIATE;

ALTER TABLE BOARD_GROUP ADD CONSTRAINT PK_BOARD_GROUP PRIMARY KEY (bgr_id);

	

/* 게시판 카테고리 */
create table board_cate(
	brd_cate_id NUMBER(12) NOT NULL, /*PK*/
	board_group_id NUMBER(12) NOT NULL, /* 게시판 종류 - fk*/
	brd_cate_key varchar2(20) NOT NULL, /* 카테고리 key값 -> 파라미터에 씀*/
	brd_cate_name varchar2(30) NOT NULL, /* 카테고리 이름 */
	brd_parent NUMBER(12) NOT NULL, /* 상위카테고리 - fk  */
	brd_cate_order NUMBER(12) NOT NULL /* 카테고리 순서 */
)SEGMENT CREATION IMMEDIATE;

ALTER TABLE board_cate ADD CONSTRAINT PK_BOARD_CATE PRIMARY KEY (brd_cate_id);

ALTER TABLE board_cate ADD CONSTRAINT 
	FK_BRG_TO_BC FOREIGN KEY(board_group_id) REFERENCES BOARD_GROUP(bgr_id);
	
--ALTER TABLE board_cate ADD CONSTRAINT FK_BC_TO_BC FOREIGN KEY(brd_parent) REFERENCES board_cate(brd_cate_id);
--alter table board_cate drop constraint fk_bc_to_bc;

	
/* 게시판 */
create table board (
	brd_id NUMBER(12) NOT NULL, /* PK */
	bgr_id NUMBER(12) NOT NULL,/* 게시판 종류 - fk */
	mem_id varchar2(20) NOT NULL, /* 글쓴이 - fk  */
	mem_nickname varchar2(20) not null, /*쓴사람 닉네임 -> 검색용이*/
	brd_title varchar(200) NOT NULL, /* 제목 */
	brd_content varchar2(4000) NOT NULL,/* 내용 */
	brd_hit NUMBER(12) default 0 NOT NULL, /* 조회수*/
	regdate DATE default sysdate NOT NULL /* 등록일 */
)SEGMENT CREATION IMMEDIATE;


ALTER TABLE BOARD ADD CONSTRAINT PK_BOARD PRIMARY KEY (brd_id);

ALTER TABLE BOARD ADD CONSTRAINT 
	FK_BGR_TO_BOARD FOREIGN KEY(bgr_id) REFERENCES BOARD_GROUP(bgr_id);
	
ALTER TABLE BOARD ADD CONSTRAINT
	FK_MEMBER_T0_BOARD FOREIGN KEY(mem_id) REFERENCES MEMBER(id);



/* 첨부파일 */
create table img_file (
	img_id NUMBER(12) NOT NULL, /*PK*/
	brd_id NUMBER(12) NOT NULL, /* 게시판아이디 - fk */
	mem_id varchar2(20) NOT NULL, /* 작성한 회원 아이디 - fk */
	img_name varchar2(30) NOT NULL, /* 파일 이름 */
	img_path varchar2(200) NOT NULL, /* 파일 경로 */
	regdate DATE NOT NULL /* 등록일 */
)SEGMENT CREATION IMMEDIATE;

ALTER TABLE img_file ADD CONSTRAINT PK_IMG_file PRIMARY KEY (img_id);

ALTER TABLE img_file ADD CONSTRAINT 
	FK_BRD_TO_IMG_file FOREIGN KEY(brd_id) REFERENCES BOARD(brd_id);
	
ALTER TABLE IMG_file ADD CONSTRAINT
	FK_MEMBER_T0_IMG_file FOREIGN KEY(mem_id) REFERENCES MEMBER(id);

	

/* 게시판 - 댓글 */
create table board_comment(
	cmt_id NUMBER(12) NOT NULL, /*PK*/
	brd_id NUMBER(12) NOT NULL, /* 게시판아이디 - fk */
	mem_id varchar2(20) NOT NULL, /* 작성한 회원 아이디 - fk */
	cmt_lv NUMBER(12) NOT NULL, /* depth */
	cmt_seq NUMBER(12) NOT NULL, /* 같은 depth내 순서 */
	cmt_content varchar2(4000) NOT NULL,/* 댓글 내용*/
	regdate DATE NOT NULL /* 등록일 */
)SEGMENT CREATION IMMEDIATE;

ALTER TABLE board_comment ADD CONSTRAINT PK_CMT_ID PRIMARY KEY (cmt_id);

ALTER TABLE board_comment ADD CONSTRAINT 
	FK_BRD_TO_cmt FOREIGN KEY(brd_id) REFERENCES BOARD(brd_id);
	
ALTER TABLE board_comment ADD CONSTRAINT
	FK_MEMBER_T0_cmt FOREIGN KEY(mem_id) REFERENCES MEMBER(id);

