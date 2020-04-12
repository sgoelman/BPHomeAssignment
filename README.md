_**Instructions on running the project**_

REST Controller API Mapping:
 
Project Goals :
    1.Stateless
    2.Asynchronous code 
    3.Using rxJava


_***Three things you would improve in my submission:***_

**1. Use a database and update data after a block of 10,000 inputs:**
    This will require service to be updated on each request ,current_count + db_count .
    Inorder fo us to be updated on realtime 

**2. Create Dockers when load is high:**
    For example if this service maximum load is 10,000 requests per second and the requests are 1-million per second 
    a load balancer will create 10 new docker containers and there will be a second service that will allocate the requests in an even way to each docker.
 

**4.Data should be in Alphabetical order and Data Type that holds the data should a b-tree:** 
    When our data is arranged in an Alphabetical order , it will take less time to find the required data.
    Instead of a list use a Alphabetical b-tree - where the roots contain data and the leaves guide us to the required data
    Each operation is O(logn) instead of  O(n) - in lists  
