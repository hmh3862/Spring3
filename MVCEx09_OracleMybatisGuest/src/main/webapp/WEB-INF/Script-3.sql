SELECT * FROM tab;

DROP SEQUENCE guest_idx_seq;
CREATE SEQUENCE guest_idx_seq;

DROP TABLE guest;
CREATE TABLE guest(
	idx NUMBER PRIMARY KEY, -- 키 필드
	REF NUMBER DEFAULT 0,   -- 원본글 번호
	seq NUMBER DEFAULT 0,   -- 같은 그룹의 나타날 순서
	lev NUMBER DEFAULT 0,   -- 몇 단계 답변이냐
	name 		varchar2(50) NOT NULL,
	password 	varchar2(50) NOT NULL,
	content 	varchar2(4000) NOT NULL,
	regDate     timestamp DEFAULT sysdate,
	ip 		varchar2(50) NOT NULL,
	del     char(1) DEFAULT 'N'
);
SELECT * FROM tab;
-- 원본글 3개
INSERT INTO guest VALUES (guest_idx_seq.nextval,1,0,0,'주인장1','1234','아마도 내용1',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,2,0,0,'주인장2','1234','아마도 내용2',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,3,0,0,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
SELECT * FROM guest;

-- 1번글의 답변 2개
INSERT INTO guest VALUES (guest_idx_seq.nextval,1,1,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,1,2,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
SELECT * FROM guest;

-- 2번글의 답변 2개
INSERT INTO guest VALUES (guest_idx_seq.nextval,2,1,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,2,2,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
SELECT * FROM guest;

-- 순서대로 불러오는 쿼리
SELECT * FROM guest ORDER BY REF DESC, seq ASC;

COMMIT;


--ALTER TABLE GUEST ADD DEL CHAR(1);
--SELECT * FROM guest;
--
--UPDATE guest SET del='N';
--SELECT * FROM guest;
--COMMIT;
--
--ALTER TABLE JSPUSER.GUEST MODIFY DEL CHAR(1) DEFAULT 'N' NOT NULL;



