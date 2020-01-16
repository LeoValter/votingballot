##The best restaurant voting system.

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

`The task is:`

####Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and **README.md with API documentation and curl commands to get data for voting and vote.**

---
P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.

---

<pre>
SERVER_PATH for app http://localhost:8080/votingballot
</pre>

## Admin flow 

#### get All Users
`curl -s http://localhost:8080/votingballot/admin/users --user admin@gmail.com:admin`

#####Response example:
<pre>
[ { "id":100000,
    "name":"Admin",
    "email":"admin@gmail.com",
    "password":"{noop}admin",
    "enabled":true,
    "registered":"2020-01-16T18:01:38.738+0000",
    "roles":["ROLE_ADMIN","ROLE_USER"]
  },
    
  { "id":100001,
    "name":"User",
    "email":"user@gmail.com",
    "password":"{noop}password",
    "enabled":true,
    "registered":"2020-01-16T18:01:38.738+0000",
    "roles":["ROLE_USER"]
  }
]
</pre>

#### get Users 100001
`curl -s http://localhost:8080/votingballot/admin/users/100001 --user admin@gmail.com:admin`

#####Response example:
<pre>
  { "id":100001,
    "name":"User",
    "email":"user@gmail.com",
    "password":"{noop}password",
    "enabled":true,"registered":"2020-01-16T18:01:38.738+0000",
    "roles":["ROLE_USER"]
  }
</pre>


#...Full documentation will be published on 17/01/2020...

### Thank you for understanding!