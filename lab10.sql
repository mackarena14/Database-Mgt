create or replace function preReqsFor(int, REFCURSOR) returns refcursor as 
$$
declare
   course_Num int       := $1;
   resultset   REFCURSOR := $2;
begin
   open resultset for 
      select preReqNum
      from   Prerequisites
      where courseNum = course_Num;
   return resultset;
end;
$$ 
language plpgsql;

select preReqsFor(308, 'results');
Fetch all from results;

create or replace function isPreReqFor(int, REFCURSOR) returns refcursor as 
$$
declare
   course_Num int       := $1;
   resultset   REFCURSOR := $2;
begin
   open resultset for 
      select courseNum
      from   Prerequisites
       where  prereqNum=course_Num;
   return resultset;
end;
$$ 
language plpgsql;

select isPreReqFor(308, 'results2');
Fetch all from results2;

