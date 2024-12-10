<<<<<<< HEAD
# java-interview-api-main
=======
## Java backend (server) application
1. Clone this folder by executing the git command: git clone https://github.com/vimalraj6474/java-interview-api-main.git
2. Navigate to the cloned directory java-interview-api-main
3. Build the backend docker image by executing the command: docker build -t vimalraj6474/widget-server-app:latest .
4. Run the backend app by executing the command: docker run -d -p 9000:9000 --name my-java21-app vimalraj6474/widget-server-app:latest

## Testing 
Execute the following curl commands to test the backend java application
Testing the CRUD Endpoints
Use curl commands to test each endpoint:

Create Widget:
curl -X POST -H "Content-Type: application/json" \
     -d '{"name": "Widget1", "description": "A test widget", "price": 100.50}' \
     http://localhost:9000/v1/widgets

curl -X POST -H "Content-Type: application/json" \
     -d '{"name": "Widget2", "description": "A test widget", "price": 10.50}' \
     http://localhost:9000/v1/widgets

curl -X POST -H "Content-Type: application/json" \
     -d '{"name": "Widget3", "description": "A test widget", "price": 70.50}' \
     http://localhost:9000/v1/widgets

curl -X POST -H "Content-Type: application/json" \
     -d '{"name": "Widget4", "description": "A test widget", "price": 5.50}' \
     http://localhost:9000/v1/widgets


Get All Widgets:
curl -X GET http://localhost:9000/v1/widgets

Get Widget by Name:
curl -X GET http://localhost:9000/v1/widgets/Widget1

Update Widget:
curl -X PUT -H "Content-Type: application/json" \
     -d '{"description": "Updated description", "price": 150.75}' \
     http://localhost:9000/v1/widgets/Widget1

Delete Widget:
curl -X DELETE http://localhost:9000/v1/widgets/Widget1

=================================================================================================================================

Edge case Test Scenarios

Create a Widget with Invalid Data:
curl -X POST -H "Content-Type: application/json" \
     -d '{"name": "Wi", "description": "Test", "price": -1}' \
     http://localhost:9000/v1/widgets

Create a Widget with Duplicate Name:
curl -X POST -H "Content-Type: application/json" \
     -d '{"name": "Widget1", "description": "Duplicate", "price": 100}' \
     http://localhost:9000/v1/widgets

Fetch a Non-Existent Widget:
curl -X GET http://localhost:9000/v1/widgets/NonExistentWidget

Update a Non-Existent Widget:
curl -X PUT -H "Content-Type: application/json" \
     -d '{"description": "Updated", "price": 200}' \
     http://localhost:9000/v1/widgets/NonExistentWidget

Delete a Non-Existent Widget:
curl -X DELETE http://localhost:9000/v1/widgets/NonExistentWidget


