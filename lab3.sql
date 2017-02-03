--1
SELECT ordnumber, totalusd
FROM Orders;

--2
SELECT name, city
FROM Agents
WHERE name = 'Smith';

--3
SELECT pid , name, priceusd
FROM products
WHERE quantity> 200100;

--4
SELECT name, city
FROM Customers
WHERE city='Duluth';

--5
SELECT name
FROM Agents
WHERE city != 'New York' and city != 'Duluth';

--6
SELECT * 
FROM Products
WHERE city !='Dallas' and city != 'Duluth' and priceusd>=1;

--7
SELECT * 
FROM Orders
WHERE month = 'Feb' or month = 'May';


--8
SELECT * 
FROM Orders
WHERE month = 'Feb' and totalusd >= 600;


--9
SELECT * 
FROM Orders
WHERE cid= 'c005';

