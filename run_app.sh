
docker build -t app_server .
docker run -d -p 9000:9000 --name my-java21-app app_server
