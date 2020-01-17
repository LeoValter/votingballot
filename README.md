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

##Profiles

### Registration

`curl -s -X POST -d '{"name":"UserName","email":"username@gmail.com","password":"password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/users/register`

#####Response example:
<pre>
  { "id":100022,
    "name":"UserName",
    "email":"username@gmail.com",
    "password":"{bcrypt}$2a$10$nPBfsT9y9S1/VkcUqfeVm.n963.SdCg7pp7FpNpI13eWCdWn2Rj0q",
    "enabled":true,"registered":"2020-01-17T11:00:55.238+0000",
    "roles":["ROLE_USER"]
  }
</pre>


### Admins

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

#### get User 100001
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

#### Create Users

`curl -s -X POST -d '{"name":"SomeUsers","email":"someuser@gmail.com","password":"somepassword","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/admin/users --user admin@gmail.com:admin`

#####Response example:
<pre>
  { "id":100022,
    "name":"SomeUsers",
    "email":"someuser@gmail.com",
    "password":"{bcrypt}$2a$10$2wBBA02jxYFqM86n18AN9eH1uRd8jP8PXtT5IK2NfHNURbweFT536",
    "enabled":true,
    "registered":"2020-01-17T09:29:17.307+0000",
    "roles":["ROLE_USER"]
  }
</pre>

#### Update User 

`curl -s -X PUT -d '{"id":100022, "name":"SomeUsers Update Name","email":"someuser@gmail.com","password":"newsomepassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/admin/users --user admin@gmail.com:admin`

#####Response example:
<pre>
  { "id":100022,
    "name":"SomeUsers Update Name",
    "email":"someuser@gmail.com",
    "password":"{bcrypt}$2a$10$4ldMjpj80Liz/UgoyjPcfOMY4rZ3eP9dUTYdr6zvY4V9TnFqhTVwS",
    "enabled":true,
    "registered":"2020-01-17T11:18:36.456+0000",
    "roles":["ROLE_USER"]
  }
</pre>

#### Delete User 100022

`curl -s -X DELETE  http://localhost:8080/votingballot/admin/users/100022 --user admin@gmail.com:admin`


### Users

#### Get Current User

`curl -s http://localhost:8080/votingballot/users --user user@gmail.com:password`

#####Response example:
<pre>
  { "id":100001,
    "name":"User",
    "email":"user@gmail.com",
    "password":"{noop}password"
  }
</pre>

#### Update Current User

`curl -s -X PUT -d '{"id":100001,"name":"NewName","email":"user@gmail.com","password":"password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/users --user user@gmail.com:password`


#### delete Current User

`curl -s -X DELETE http://localhost:8080/votingballot/users --user user@gmail.com:password`

#...Full documentation will be published on 17/01/2020...

### Thank you for understanding!