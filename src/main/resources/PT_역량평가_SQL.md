## 테이블 생성 SQL

-- CREATE GUEST
CREATE TABLE GUEST (
    USER_ID VARCHAR(30) PRIMARY KEY,
    PWD VARCHAR(30) NOT NULL,
    E_NUM VARCAHR(30) NOT NULL, 
    EMAIL VARCHAR(50) NOT NULL,
    NAME VARCHAR(50) NOT NULL
);



-- CREATE QUESTION
-- 's' : speech, 'p' : presentation, 'u' : unrest, 'e' : evaluation
CREATE TABLE QUESTION (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    CATEGORY VARCHAR(1) NOT NULL,
    CONTENT VARCHAR(150) NOT NULL,
    USE_YN VARCHAR(1) NOT NULL 
);



-- CREATE RESULT
CREATE TABLE RESULT (
    USER_ID VARCHAR(30) NOT NULL,
    CATEGORY VARCHAR (1) NOT NULL,
    SCORE INT(3) NOT NULL,
    WORK_DTIM VARCHAR(14) NOT NULL 
    
	FOREIGN KEY(USER_ID) REFERENCES GUEST(USER_ID),
	PRIMARY KEY(USER_ID, CATEGORY , WORK_DTIM)

);



## 카테고리별 질문 조회

-- SELECT QUESTION
SELECT ID          AS id
      , CATEGORY  AS category
      , CONTENT   AS content
  FROM QUESTION
-- WHERE category = #{category}
 WHERE category = 's'
ORDER BY ID;


 
## 결과 조회, 입력, 수정, 삭제


-- GUEST INSERT 
INSERT INTO GUEST (USER_ID, PWD, E_NUM, EMAIL , NAME) VALUES (‘park’,’1111’,’0001’,'park@lotte.net', '박지성');
INSERT INTO GUEST (USER_ID, PWD, E_NUM, EMAIL , NAME) VALUES (‘lee’,’1111’,’0002’, 'lee@lotte.net', '이수근');
INSERT INTO GUEST (USER_ID, PWD, E_NUM, EMAIL , NAME) VALUES (‘kang’,’1111’,’0003’, 'kang@lotte.net', '강호동');


--QUESTION INSERT
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '도입부에서 청중의 주의를 끄는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '태도는 겸손하게 진행 하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '주제를 분명히 소개 하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '내용이 논리적이고 일관성이 있는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '핵심 주제와 소주제를 명확히 밝히는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '내용구성이 분명한가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '실례, 근거자료 등을 활용하여 설명하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '스피치에 열의와 자신감이 넘치는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '2개 이상의 인용된 지지 자료가 있는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '적절한 지지 자료를 사용했는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '논점을 분명히 요약하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '강조된 끝맺음을 하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '강조된 끝맺음을 하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '강조된 끝맺음을 하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '청중을 골고루 살피는 시선처리를 하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '제스처가 자연스로운가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '차분하고 안정된 스피치 상태인가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '표정은 편안하게 또느 미소 짓고 있는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '분명하게 발음하는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '목소리의 크기는 적절한가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '말의 속도는 적절한가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '음성적 군말 (어,저 등)을 사용하지 않는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '적절히 호흡을 하고 쉬는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '목소리에 적절한 고저가 있는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '목소리에 강약이 있는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '준비는 충분하였는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '자신이 준비한 내용을 100% 전달하였는가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '스피치 내용이 청중에게 적절한가');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('s', '정해진 시간 안에 끝내는가');



	
 

-- UPDATE RESULT
UPDATE RESULT
SET    SCORE = '99'
 WHERE USER_ID = 'test01'
   AND QUESTION_ID = '1';



-- DELETE RESULT
DELETE FROM RESULT WHERE USER_ID = 'test01';


-- SELECT RESULT SUM EACH CATEGORY USING LISTAGG (LISTAGG is not supported in H2)
SELECT a.USER_ID        AS userId
      , SUM(CASE WHEN b.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult
      , SUM(CASE WHEN b.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult
      , SUM(CASE WHEN b.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult
      , SUM(CASE WHEN b.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult
  FROM RESULT a
      , QUESTION b
 WHERE a.QUESTION_ID = b.ID
--   AND a.USER_ID = #{user_id}
  AND a.USER_ID = 'test01';



