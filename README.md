# microservice

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
localhost:8081/oauth/authorize?response_type=code&client_id=fooClientIdPassword&redirect_uri=http://lin.com&scope=read
localhost:8081/oauth/token?grant_type=authorization_code&client_id=fooClientIdPassword&redirect_uri=http://lin.com&code=

# OAuth2 implicit flow
localhost:8081/oauth/authorize?response_type=token&client_id=sampleClientId&redirect_uri=http://lin.com&scope=read

# OAuth2 password flow
