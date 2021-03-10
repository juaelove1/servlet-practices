desc emaillist;


-- insert
insert into emaillist values(null,'김','지수','juaelove3@skuniv.ac.kr');
insert into emaillist values(null,'김','소이','thdlal333@skuniv.ac.kr');

-- list
select no,first_name,last_name,email from emaillist order by no desc;