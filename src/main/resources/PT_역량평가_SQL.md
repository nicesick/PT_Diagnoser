## 테이블 생성 SQL

-- CREATE GUEST
CREATE TABLE GUEST (
    USER_ID VARCHAR(30) PRIMARY KEY,
    PWD VARCHAR(30) NOT NULL,
    E_NUM VARCHAR(30) NOT NULL, 
    EMAIL VARCHAR(50) NOT NULL,
    NAME VARCHAR(50) NOT NULL
);



-- CREATE QUESTION
-- 's' : speech, 'p' : presentation, 'u' : unrest, 'e' : evaluation
CREATE TABLE QUESTION (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    CATEGORY VARCHAR(1) NOT NULL,
    CONTENT VARCHAR(150) NOT NULL,
    USE_YN VARCHAR(1) DEFAULT 'Y'
);



-- CREATE RESULT
CREATE TABLE RESULT (
    USER_ID VARCHAR(30) NOT NULL,
    CATEGORY VARCHAR (1) NOT NULL,
    SCORE INT(3) NOT NULL,
    WORK_DTIM VARCHAR(14) DEFAULT FORMATDATETIME(SYSDATE, 'yyyyMMddHHmmss'),
    

	FOREIGN KEY(USER_ID) REFERENCES GUEST(USER_ID),
	PRIMARY KEY(USER_ID, CATEGORY , WORK_DTIM)

);



-- CREATE CATEGORY
CREATE TABLE CATEGORY (
	id VARCHAR(1) PRIMARY KEY,
	title VARCHAR(50) NOT NULL 
);



-- CREATE SCORE_DETAIL

CREATE TABLE SCORE_DETAIL (
	ID VARCHAR(1),
	FROM_SCORE NUMBER(3),
	TO_SCORE NUMBER(3),
	DETAIL VARCHAR(50) NOT NULL,
	DESCRIPTION VARCHAR(500) NOT NULL,
	

	PRIMARY KEY(ID, FROM_SCORE)

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
INSERT INTO GUEST (USER_ID, PWD, E_NUM, EMAIL , NAME) VALUES ('park','1111','0001','park@lotte.net', '박지성');
INSERT INTO GUEST (USER_ID, PWD, E_NUM, EMAIL , NAME) VALUES ('lee','1111','0002','lee@lotte.net', '이수근');
INSERT INTO GUEST (USER_ID, PWD, E_NUM, EMAIL , NAME) VALUES ('kang','1111','0003','kang@lotte.net', '강호동');

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



INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '성공적 프레젠테이션 전략과 스킬을 가지고 있는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '성공 프리젠터가 되기 위한 구체적인 목표와 계획이 존재하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '항상 자신감 넘치고 당당한 모습으로 프레젠테이션을 하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '프레젠테이션 진행시 청중들에게 감동을 주고 있는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '프레젠테이션 전에 철저히 사전 조사를 하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '프레젠테이션 시작 전 시간적 여유를 갖고 준비하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '작성된 문장이 논리성을 갖추고 있는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '프레젠테이션 소프트웨어를 능숙하게 다루고 구성이 가능한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '자신감 있는 목소리와 명확한 발음 발성으로 전달하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '목소리의 고저를 활용하여 효과적으로 전달하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '효과적인 커뮤니케이션 기법을 알고 이를 적용하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '설득적 커뮤니케이션 기법을 알고 있고 이를 적용하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '시선처리, 표정처리 하는 방법을 알고 이를 적용하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '제스처 활용법과 동선 이동법을 알고 이를 적용하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '프레젠테이션 비주얼의 중요성을 알고 작성이 용이한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '키워드, 도형이나 필토그램, 여백 등을 활용하여 SIMPLE 하게 만들어져 있는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '창조적이고 신선한 요소가 존재하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '전체적으로 설득력이 있는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '노트북, 프로젝터, 마이크, 포인터를 사용이 자유로운가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('p', '사전에 장소, 장비들을 확인하고 충분히 리허설을 실시하는가?');



INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '대중 앞에서 발표할 기회를 즐거운 마음으로 기다린다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '연단 위에 있는 물건들을 만지려고 할 때 손이 떨린다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '발표할 내용을 잊어버릴까 봐 두렵다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '내가 청중들에게 말할 때 그들이 우호적인 것으로 보인다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '발표 준비를 하는 동안, 나는 불안하다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '발표를 끝낼 때, 나는 즐거운 경험을 했다고 느낀다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 몸동작을 많이 사용하거나 목소리를 표현력 있게 내는 것을 싫어한다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 청중 앞에서 발표할 때, 생각이 혼란되고 뒤죽박죽 된다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '청중들을 대하는 것이 두렵지 않다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '발표하기 직전에는 신겨이 예민해지지만, 금방 두려움을 잊고 그 경험을 즐긴다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 아주 자신 있게 발표할 것이라고 예상한다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '발표를 하는 동안 나는 아주 침착하다고 느낀다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 친구들과는 유창하게 말하지만, 연단 위에 서면 할 말이 생각하지 않는다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '발표를 하는 동안 나는 긴장되지 않고 편안하게 느낀다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 대중 앞에서 발표하는 것을 즐기지도 않지만, 특별히 두려워하지도 않는다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '가능하다면 대중 앞에서 발표하는 것을 피한다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '청중들을 쳐다볼 때 그들의 얼굴이 흐린하게 보인다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '여러 사람들 앞에서 발표하려고 애쓴 후에는 내 자신이 싫어진다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 발표 준비하는 것을 즐긴다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 청중을 대할 때 정신이 또렷해진다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 발표할 때 말을 잘 한다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 발표하러 일어서기 직전에 땀이 나고 떨린다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '내 자세가 긴장되고 부자연스럽게 느껴진다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '여러 사람들 앞에서 발표하는 동안 나는 두렵고 긴장된다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '발표할 기회는 즐겁다고 생각한다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '내 생각을 표현할 정확한 말들을 차분하게 찾기가 어렵다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '대중 앞에서 발표한다고 생각하면 겁난다.');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('u', '나는 청중 앞에 서면 정신이 맑아지는 느낌이 든다.');



INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '고객 니즈의 표현 여부는?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '솔루션과 고객의 효용 표현 여부는?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '차별화 전략의 명확성은 어떠한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '서론에서 관심 유도 동기부여, 개관 여부는?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '본론 내용의 논리성, 설득력 표현은?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '본론 내용의 리뷰, 재동기부여, 마무리는 어떠한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '슬라이드 간결하여 알아보기 쉬운가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '디자인과 스토리라인 콘셉트가 조화로운가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '그래픽의 핵심 차별화 요소 전달력은 어떠한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('c', '흥미와 설득력 있는가?');

INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '자신감 있고 열정적인 어조인가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '시선처리와 손동작 움직임은 어떠한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '내용 숙지 후 고객을 보며 진행하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '말하는 태도와 발음의 명확성은 있는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '발표 내용의 길이가 간결한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '간결하고 명확한 답변을 하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '질문에 적절한 대응(보조슬라이드 준비 여부, 성실성 여부)하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '신뢰감을 주기에 충분한 발표 내용과 논조있게 하는가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '고객 관점의 진행과 태도는 어떠한가?');
INSERT INTO Question (CATEGORY , CONTENT ) VALUES ('f', '시간 관리와 진행 능력은 있는가?');



-- CATEGORY INSERT
INSERT INTO CATEGORY VALUES('s', '스피치진단');
INSERT INTO CATEGORY VALUES('p', '프레젠테이션진단');
INSERT INTO CATEGORY VALUES('u', '발표불안진단');
INSERT INTO CATEGORY VALUES('e', '발표평가진단');



-- SCORE_DETAIL INSERT
INSERT INTO SCORE_DETAIL VALUES('s', 0, 45, '훈련필요', '직장인 평균에 미치지 못합니다. 기초훈련부터 하나씩 훈련을 시작하셔야 합니다.');
INSERT INTO SCORE_DETAIL VALUES('s', 46, 53, '보통', '직장인 평균에 해당합니다. 성공적인 스피치를 위해 한발 도약하시기 바랍니다.');
INSERT INTO SCORE_DETAIL VALUES('s', 54, 67, '우수', '조금만 더 훈련하신다면 탁월한 스피치를 할 수 있는 자질이 있습니다.');
INSERT INTO SCORE_DETAIL VALUES('s', 68, 100, '탁월', '훈련하지 않으신 분이라면 이미 스피치를 잘 하고 있습니다.');

INSERT INTO SCORE_DETAIL VALUES('p', 0, 59, '훈련필요', '직장인 평균 이하입니다. 전문가를 통한 지속적인 노력이 필요합니다.');
INSERT INTO SCORE_DETAIL VALUES('p', 60, 69, '보통', '직장인 평균입니다. 많은 노력으로 훈련해 나가신다면 능숙한 프레젠터가 될 수 있습니다.');
INSERT INTO SCORE_DETAIL VALUES('p', 70, 79, '우수', '기본적인 자질을 갖추고 계십니다만 좀 더 적극적인 노력을 하신다면 탁월한 프레젠터가 되실 수 있습니다.');
INSERT INTO SCORE_DETAIL VALUES('p', 80, 100, '탁월', '탁월한 프레젠터입니다. 이미 탁월한 역량을 가지고 계십니다.');

INSERT INTO SCORE_DETAIL VALUES('u', 85, 100, '상담필요', '발표상황에서 상당한 정도로 불편감과 불안을 경험하고 있으며, 여러 발표상황들을 회피하기도 합니다. 이러한 불편감과 회피가 지속되면 전문가를 찾아 상담할 것을 권합니다.');
INSERT INTO SCORE_DETAIL VALUES('u', 38, 84, '보통', '발표상황에서 중간 정도의 불안을 경험하고 때때로 발표상황을 회피하기도 합니다. 그러나 그렇게까지 문제되지는 않습니다.');
INSERT INTO SCORE_DETAIL VALUES('u', 0, 37, '좋음', '발표상황에서 불안 수준이 낮은 편으로서 그다지 불편감과 불안을 느끼지 않습니다.');

INSERT INTO SCORE_DETAIL VALUES('e', 0, 59, '훈련필요', '직장인 평균 이하입니다. 전문가를 통한 지속적인 노력이 필요합니다.');
INSERT INTO SCORE_DETAIL VALUES('e', 60, 69, '보통', '직장인 평균입니다. 많은 노력으로 훈련해 나가신다면 능숙한 프레젠터가 될 수 있습니다.');
INSERT INTO SCORE_DETAIL VALUES('e', 70, 79, '우수', '기본적인 자질을 갖추고 계십니다만 좀 더 적극적인 노력을 하신다면 탁월한 프레젠터가 되실 수 있습니다.');
INSERT INTO SCORE_DETAIL VALUES('e', 80, 100, '탁월', '탁월한 프레젠터입니다. 내용전달 및 플랫폼 스킬에 탁월한 역량을 가지고 계십니다.');



-- UPDATE RESULT
UPDATE RESULT
SET    SCORE = '99'
 WHERE USER_ID = 'test01'
   AND QUESTION_ID = '1';



-- DELETE RESULT
DELETE FROM RESULT WHERE USER_ID = 'test01';



-- SELECT RESULT SUM EACH CATEGORY USING LISTAGG (LISTAGG is not supported in H2)
SELECT a.USER_ID        AS userId
      , SUM(CASE WHEN a.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult
      , SUM(CASE WHEN a.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult
      , SUM(CASE WHEN a.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult
      , SUM(CASE WHEN a.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult
  FROM RESULT a
--   AND a.USER_ID = #{user_id}
  AND a.USER_ID = 'test01'

GROUP BY a.USER_ID;



**-- SELECT RESULT SUM EACH CATEGORY 변경사항**
-- 2020.10.12 repository 단에서 같은 workDtim 별 결과로 가공합니다. + 2020.10.15 score_100 추가, category 테이블 use_yn 추가

SELECT userId
       , workDtim
       , score
       , title
       , detail
       , description
FROM
(SELECT a.USER_ID        AS userId
       , TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd')     AS workDtim
       , a.SCORE_100     AS score
       , b.TITLE         AS title
	   , c.DETAIL		 AS detail
	   , c.DESCRIPTION	 AS description
	   , ROW_NUMBER() OVER(PARTITION BY a.USER_ID, TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'),'yyyy-MM-dd'), a.CATEGORY ORDER BY a.SCORE DESC) AS row_num
  FROM RESULT a
	   , CATEGORY b
	   , SCORE_DETAIL c
  WHERE a.CATEGORY = b.ID
	AND a.CATEGORY = c.ID
    AND a.USER_ID = 'park'
	AND a.SCORE BETWEEN c.FROM_SCORE AND c.TO_SCORE
	AND b.USE_YN = 'Y'
ORDER BY TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd') DESC, a.CATEGORY
)
WHERE row_num = 1;

