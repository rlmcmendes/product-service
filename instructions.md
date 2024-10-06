# Instructions for CTT Exercise

## Running the Exercise Locally

### Prerequisites

1. **Docker**: Ensure you have Docker installed on your machine. You can download it from [Docker's official website](https://www.docker.com/get-started).
2. **Docker Compose**: This is usually included with Docker Desktop, but if you need it separately, refer to [Docker Compose installation instructions](https://docs.docker.com/compose/install/).
3. **Java Development Kit (JDK)**: Ensure you have JDK 11 installed if you are running tests or building the project outside of Docker.

### Running the project locally using docker-compose

#### Using rlmendes/product-service in docker hub
In order to run the project locally only using docker multi containers, run the following commands:
```bash
docker-compose up
````
Now the http://localhost:8080 endpoint must be up and running.

#### Testing new feature

If you want to add a new feature and try it locally, 

1. Delete **image: "rlmendes/product-service:latest"** from the docker-compose file, so docker won't pull the old version.
2. Run the folllowing commands:
```bash
mvn clean package
docker-compose up
````

### Running the project locally using kubernetes

For this running process make sure you have a kubernets cluster up and running. If not,
make sure to visit [Kuberenetes' official website setup](https://kubernetes.io/docs/setup/).

Run the following commands:
```bash
kubectl apply -f k8s-manifest.yaml
kubectl port-forward service/microservice 8080:8080 -n product-service
kubectl port-forward service/mongo-service 27017:27017 -n product-service
```

### Run Postman

Now the http://localhost:8080 endpoint must be up and running. Use Postman and try it using:

1. **/register** ->
   Request Body = { "description": "<PRODUCT_DESCRIPTION>",
   "categories": \[{"id": "<ID_CATEGORY>", "name": "<NAME_CATEGORY>"}],
   "price": "<PRICE_AS_NUMBER>"}
2. **/information** -> Request Body = {"id":"<ID_TO_GET>"}

## Assumptions

 - I assumed that stock initializes to 0 and is not updated in our service.
 - Categories will receive list of id and name objects, however, the name will not be persisted.
 - Price is received as a String but persisted as a float.
 - I assumed the PR instruction in GitHub pipeline should be a fork, as we do not have the name of the owner nor the 
name of the manifest repository to make the PR to.

## Improvements in Production

 - **Error Handling**: Implement comprehensive error handling and logging to better track issues in production.
 - **Monitoring and Alerts**: Introduce monitoring tools to keep track of application health and performance, and set up 
alerts for anomalies, such as DataDog or Prometheus.
 - **Security**: Usage of API Gateway (f.e IBM API Gateway) to properly handle authentication and authorization and usage of HTTPS.
 - **CI/CD Enhancements**: Implement more stages in the CI/CD pipeline, such as, static code analysis using SonarQube for instance,
rollback mechanisms and deployment.
 - **Replicas**: Depending on the clients number, as well as read/write operations, the replica number should be scaled 
accordingly.
 - **Proper Task Distribution**: In a production project we should make use of JIRA platform, for example, to make sure
the User Stories are well-defined and associate them to tasks. 
 - **Caching Strategies**: Introduce caching mechanisms (f.e. Redis, Memcached) to improve response times and reduce 
the load on the service.
    