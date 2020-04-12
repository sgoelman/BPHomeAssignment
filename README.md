_**Instructions on running the project**_


**REST Controller API Mapping:**
  
 1.Get a count of all the events: _http://localhost:8081/events/_
 2.Get a count of a specific event: _http://localhost:8081/events/{name}_ 
    example : http://localhost:8081/events/bar 
 3.Get all events data count: _http://localhost:8081/events/data_
 4.Get a count of a specific event data: _http://localhost:8081/events/data/{data}_
    example : http://localhost:8081/events/data/sit 
 
 
Project Goals :
    1.Stateless
    2.Asynchronous code 
    3.Using rxJava


_***Three things you would improve in my submission:***_

**1. Use a database and update data after a block of 10,000 inputs:**
    This will enable us to cope with  an infinite amount of data 

**2. Create Dockers when load is high:**
    For example if this service maximum load is 10,000 requests per second and the requests are 1-million per second 
    a load balancer will create 10 new docker containers and there will be a second service that will allocate the requests in an even way to each docker.
 

**3.Working with Multiple threads :** 
    Because this service should be stateless (not reading form a provided exe file) and asynchronous.
    We should be able to execute multiple services at this same time on a single instance. 
    Meaning we can enjoy the benefits of Multi-processors 
