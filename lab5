-- 1 
select agents.city
from Agents
inner join Orders
on Orders.aid = Agents.aid
where cid = 'c006';

-- 2
select pid
from Orders
inner join Customers
on orders.cid = customers.cid
where customers.city = 'Kyoto'
order by pid asc;

-- 3
select name 
from customers
where cid not in  (select cid
              	   from Orders);
                   
-- 4
select distinct name 
from customers
left outer join Orders
on orders.cid = customers.cid
where orders.cid is null;

-- 5
select distinct customers.name, agents.name
from customers
inner join Agents
on customers.city = agents.city
inner join Orders
on customers.cid = Orders.cid
where orders.aid = agents.aid;

-- 6
select customers.name, agents.name, customers.city
from customers, agents
where customers.city=agents.city;

-- 7
select name, city
from customers
where city in (select city
               from (select city, count (*)
                     from products
					 group by city
                     order by count (*) ASC, city
					 limit 1
                    ) sub2
               )
group by customers.name, customers.city;