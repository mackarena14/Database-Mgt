-- 1
SELECT distinct city
FROM Agents
WHERE aid in 
	(SELECT aid
	FROM Orders  
	WHERE cid='c006');
   
-- 2
SELECT distinct pid
FROM Orders
WHERE cid in (SELECT cid 
              FROM Customers
              WHERE city in ('Kyoto'));
              
-- 3
SELECT cid, name
FROM Customers
WHERE cid not in (Select cid
                  from Orders
                  where aid='a01');     

-- 4
select cid 
from orders
where pid= 'p07'
and cid in (select distinct cid 
            from orders 
            where pid='p01')
                  
-- 5
select distinct pid 
from Products
where pid not in ( select cid
                  from Orders 
                  where aid = 'a08')
			order by pid desc;

-- 6
SELECT name, city, discount
FROM Customers 
WHERE cid in (Select cid 
              from Orders 
              where aid in 
					(SElECT aid 
					from Agents
					WHERE city in ('New York', 'Tokyo')));
                    
-- 7
SELECT * 
from Customers 
where discount in (SElECT discount 
                   FROM Customers
                   WHERE city in ('Duluth', 'London'));
                        
-- 8
-- Check constraints is a type of integrity constraint in sql
-- that specifies a requirement that must be met by each row in a database.
-- The check constraint only allows certain values for column(s). They are 
-- good for only allowing certain values to be entered, thus ensuring correct data
-- input,  An example of a good check constraint would be only allowing certain values 
-- for numbers, while a bad check constraint would be allowing numbers from negative
-- infinity to positive infinity. The difference between the two is that one checks for 
-- specific values, such as between 1-20, while the other check constaint is basically 
-- pointless to have if the prompt to the user specifies that it should be a number value.
-- 