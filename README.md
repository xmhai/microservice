# microservice

# Create User (POST)
localhost:9000/users
{"username":"admin","password":"123","firstName":"","lastName":"Administrator","email":"admin@lin.com","active":1}

# Update User (PUT)
localhost:9000/users
{"userName":"admin","password":"123","firstName":"","lastName":"Administrator","email":"admin@lin.com","active":1}

# Get User (GET)
localhost:9000/users/1

# Get User By Name (GET)
localhost:9000/users/username/admin

# Delete User (DELETE)
localhost:9000/users/2

