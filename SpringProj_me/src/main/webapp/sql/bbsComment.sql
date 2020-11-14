
/* Drop Tables */
/*
DROP TABLE memoComment CASCADE CONSTRAINTS;
DROP TABLE onememo CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
*/



/* Create Tables */

CREATE TABLE member
(
	id varchar2(10) NOT NULL,
	pwd varchar2(10) NOT NULL,
	name nvarchar2(10) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE memoComment
(
	cno number NOT NULL,
	lineComment nvarchar2(100) NOT NULL,
	cpostdate date DEFAULT SYSDATE,
	id varchar2(10) NOT NULL,
	no number NOT NULL,
	PRIMARY KEY (cno)
);


CREATE TABLE onememo
(
	no number NOT NULL,
	title nvarchar2(50) NOT NULL,
	content nvarchar2(2000) NOT NULL,
	postdate date DEFAULT SYSDATE,
	id varchar2(10) NOT NULL,
	PRIMARY KEY (no)
);



/* Create Foreign Keys */

ALTER TABLE memoComment
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE onememo
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE memoComment
	ADD FOREIGN KEY (no)
	REFERENCES onememo (no)
;


CREATE SEQUENCE SEQ_ONEMEMO
NOCACHE
NOCYCLE;

CREATE SEQUENCE SEQ_MEMOCOMMENT
NOCACHE
NOCYCLE;
