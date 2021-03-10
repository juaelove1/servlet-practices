desc guestbook;


insert into guestbook values(null,"김지수","1234","테스트입니다.",now());


select no,name,date_format(req_date,'%Y-%m-%d %H:%i:$s'),contents from guestbook order by req_date desc;


delete from guestbook where no = 1 and password='1234';

