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
---
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

#### Get All Users
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

#### Get User 100001
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


#### Delete Current User

`curl -s -X DELETE http://localhost:8080/votingballot/users --user user@gmail.com:password`


##Restaurants


### Get Restaurants with Today Menu

`curl -s http://localhost:8080/votingballot/restaurants/today --user user@gmail.com:password`

#####Response example:
<pre>
[  {    "id":100002,
    "name":"Шарманка",
    "dishes":[  {   "id":100007,
                    "name":"Шашлык из Каре Ягненка",
                    "price":450,
                    "date":"2020-01-17"
                 }
              ]
    },
    
    {   "id":100003,
        "name":"Тарас Бульба",
        "dishes":[  {   "id":100011,
                        "name":"Вареники Староукраинские",
                        "price":310,
                        "date":"2020-01-17"
                     }
                  ]
    },
    
    {   "id":100006,
        "name":"Ёлки Палки",
        "dishes":[  {   "id":100017,
                        "name":"Суп из белых грибов",
                        "price":175,
                        "date":"2020-01-17"
                     }
                 ]
    }
]
</pre>

### Get All Restaurants (ADMIN ONLY)

`curl -s http://localhost:8080/votingballot/admin/restaurants --user admin@gmail.com:admin`

### Get Restaurant 100002

`curl -s http://localhost:8080/votingballot/restaurants/100002 --user user@gmail.com:password`

### Create Restaurant (ADMIN ONLY)

`curl -s -X POST -d '{"name":"New Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/admin/restaurants --user admin@gmail.com:admin`

### Update Restaurant 100022 (ADMIN ONLY)

`curl -s -X PUT -d '{"name":"Update Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/admin/restaurants/100022 --user admin@gmail.com:admin`

### Delete Restaurant (ADMIN ONLY)

`curl -s -X DELETE '{"name":"Update Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/admin/restaurants/100022 --user admin@gmail.com:admin`


##Dishes


### Get All Dishes By Restaurant (ADMIN ONLY)

`curl -s http://localhost:8080/votingballot/admin/restaurants/100002/dishes --user admin@gmail.com:admin`

### Create Dish (ADMIN ONLY)

`curl -s -X POST -d '{"name":"New dish","price":199,"date":"2020-01-17"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/admin/restaurants/100002/dishes --user admin@gmail.com:admin`

#####Response example:
<pre>
  { "id":100023,
    "name":"New dish",
    "price":199,
    "date":"2020-01-17"}
</pre>

### Update Dish (ADMIN ONLY)

`curl -s -X PUT -d '{"name":"Update dish","price":199,"date":"2020-01-17"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/admin/restaurants/100002/dishes/100023 --user admin@gmail.com:admin`

### Delete Dish (ADMIN ONLY)

`curl -s -X DELETE http://localhost:8080/votingballot/admin/restaurants/100002/dishes/100023 --user admin@gmail.com:admin`


##Votes


### Get All Votes (ADMIN ONLY)

`curl -s http://localhost:8080/votingballot/admin/votes --user admin@gmail.com:admin`

#####Response example:
<pre>
[  {    "id":100019,
        "date":"2020-01-15",
        "userId":100000,
        "userName":"Admin",
        "restaurantId":100002,
        "restaurantName":"Шарманка"
    },
    
    {   "id":100020,
        "date":"2020-01-16",
        "userId":100000,
        "userName":"Admin",
        "restaurantId":100005,
        "restaurantName":"8 SECONDS PUB"
    },
    
    {   "id":100021,
        "date":"2020-01-16",
        "userId":100001,
        "userName":"User",
        "restaurantId":100004,
        "restaurantName":"Караван"
    }
]
</pre>

### Get All Votes Today (ADMIN ONLY)

`curl -s http://localhost:8080/votingballot/admin/votes/today --user admin@gmail.com:admin`

### Get Vote (ADMIN ONLY)

`curl -s http://localhost:8080/votingballot/admin/votes/100019 --user admin@gmail.com:admin`

### Get Current Profile Today Vote

`curl -s http://localhost:8080/votingballot/votes --user user@gmail.com:password`

#####Response example:
<pre>
  { "id":100021,
    "date":"2020-01-17",
    "userId":100001,
    "userName":"User",
    "restaurantId":100004,
    "restaurantName":"Караван"
  }
</pre>

### Vote for Restaurant

`curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingballot/votes/100005 --user user@gmail.com:password`

### Delete Vote (ADMIN ONLY)

`curl -s -X DELETE http://localhost:8080/votingballot/admin/votes/100019 --user admin@gmail.com:admin`
