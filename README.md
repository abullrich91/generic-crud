# generic-crud
A generic CRUD REST repository.

## Docker operations

#### Create MySQL Database

1) Download and install docker.
2) Run docker.
3) Run: ```docker run --name MySQL -p 5306:3306 -e MYSQL_ROOT_PASSWORD=abullrich mysql```
4) In a new terminal, run: ```docker exec -it MySQL bash```
5) Run: ```mysql -u root -pabullrich```
6) Run MySQL commands found in migrations script.

