SELECT * FROM tab;

CREATE SEQUENCE memo_idx_seq;

CREATE TABLE memo(
	idx 		NUMBER 			PRIMARY KEY ,
	name 		varchar2(100) 	NOT NULL ,
	password 	varchar2(100) 	NOT NULL ,
	content 	varchar2(4000) 	NOT NULL ,
	regDate 	Timestamp  		DEFAULT SYSDATE,
	ip 			varchar2(20) 	NOT NULL 
);

BEGIN 
	FOR i IN 1..134 LOOP 
		INSERT INTO memo 
	    VALUES 
	       (memo_idx_seq.nextval,'이름 '||LPAD(i,3,0), '123456', '내용입니다. '||LPAD(i,3,0), SYSDATE, '192.168.0.125');
	END LOOP;
END;
COMMIT;

SELECT count(*) FROM memo;
SELECT * FROM memo;