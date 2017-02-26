-- 1 
select distinct customers.name, customers.city
from customers
inner join Products
on products.city= customers.city
where products.city in (select city
                        from(select city, count(*)
                            from Products
                            group by city 
                            order by count DESC
                            limit 1)
                        sub2);
                            
                       		          
-- 2
select name 
from products 
where priceUSD> (select avg(priceUSD) 
                 from Products)
order by name DESC;

-- 3
select distinct customers.name, orders.pid, totalUSD
from Customers
inner join Orders
on orders.cid=customers.cid
order by totalUSD ASC;

-- 4
select  customers.name, sum(coalesce(totalUSD,0)) as "totalordered"
from Customers
left outer join Orders
on orders.cid = customers.cid
group by customers.name
order by customers.name;

-- 5 
select customers.name, products.name, agents.name
from customers
inner join orders
on customers.cid = orders.cid
inner join agents
on orders.aid = agents.aid
inner join products
on orders.pid= products.pid
where agents.city='Newark';

-- 6
select ordnumber, 
		month, 
        orders.cid,
        orders.aid, 
        orders.pid,
        orders.qty, 
        totalUSD as "totalUSD",
        (orders.qty*products.priceUSD - (orders.qty*products.priceUSD*(customers.discount/100))) as "CalculatedUSD"
from orders
inner join products
on orders.pid= products.pid
inner join customers
on orders.cid= customers.cid
where orders.totalUSD != (orders.qty*products.priceUSD -(orders.qty*products.priceUSD*(customers.discount/100)));

-- 7 
/* A left outer join will give the intersection of the data, as well as any values that exist solely 
in the first table. For example, a left outer join of customers and orders on cid will return c001-c007,
but c005 never ordered any products and thus is not in the orders table. A right outer join will do the 
opposite, returning the matching values of the table, as well as any solely in the second table referenced.
An example of this is joining the agents and customers table on cities. The query would return Duluth and
Dallas since they are matching rows with agents, but would also return Risa and Kyoto since they are values 
in the second referenced table.
*/



