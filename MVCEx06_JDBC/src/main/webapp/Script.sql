SELECT * FROM tab;

CREATE SEQUENCE emp_id_seq;

CREATE TABLE Emp(
	idx  NUMBER PRIMARY KEY,
	name varchar2(30) NOT NULL , 
	ROLE varchar2(30) NOT NULL 
); 

INSERT INTO emp VALUES (emp_id_seq.nextval, '한사람','자바프로그래머');
INSERT INTO emp VALUES (emp_id_seq.nextval, '두사람','웹프로그래머');
INSERT INTO emp VALUES (emp_id_seq.nextval, '세사람','응용프로그래머');
INSERT INTO emp VALUES (emp_id_seq.nextval, '네사람','서버프로그래머');

COMMIT;

SELECT * FROM emp;