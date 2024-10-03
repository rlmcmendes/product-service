docker build -t product-service .
docker run -p 8080:8080 --name product-service --link mongo-product-service -d product-service