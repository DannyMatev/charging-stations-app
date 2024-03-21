In order to run the application locally you need to start up the database with one of the provided docker-compose files:<br/>
```docker-compose -f docker-compose.yml up -d```
or for ARM based devices:
```docker-compose -f docker-compose-arm-based.yml up -d```

For ease of use and testing I've provided some example data in an insert script in data.sql which will populate the 
database on application startup.<br/>

In order to see the documented endpoints and test them out you can use swagger-ui docs at:<br/>
http://localhost:8080/swagger-ui/index.html

The conceptual API tests are located in `CONCEPTUAL_TESTS.md` in the root directory of the project.
