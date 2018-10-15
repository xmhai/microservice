# microservice
edge-server: 8081
eureka-server: 8761
config-server: 8888
oauth-server: 8082
user-service: 9000
web-ui: 8080

# Create User (POST)
localhost:9000/users
{"userName":"admin","password":"123","firstName":"","lastName":"Administrator","email":"admin@lin.com","active":1}

# Update User (PUT)
localhost:9000/users
{"userName":"admin","password":"123","firstName":"","lastName":"Administrator","email":"admin@lin.com","active":1}

# Get User (GET)
localhost:9000/users/1

# Get User By Name (GET)
localhost:9000/users/username/admin

# Delete User (DELETE)
localhost:9000/users/2

# Edge Server: Get User (GET)
localhost:8081/users

 
