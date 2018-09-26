# microservice
edge-server: 8081
eureka-server: 8761
config-server: 8888
oauth-server: 8082
user-service: 9000

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

# OAuth2 authorize code flow
localhost:8082/oauth/authorize?response_type=code&client_id=lin&redirect_uri=http://microservice&scope=read
localhost:8082/oauth/token?grant_type=authorization_code&redirect_uri=http://microservice&code=

# OAuth2 implicit flow
localhost:8082/oauth/authorize?response_type=token&client_id=sampleClientId&redirect_uri=http://lin.com&scope=read

# OAuth2 password flow

# Edge Server: Get User (GET)
localhost:8081/users
