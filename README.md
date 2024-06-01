# crm
 This is a crm software to store contacts and manage deals.
 
 ## Technologies I used
 This Project was created using the following technologies
 
 #### Frontend
- [Angular](https://angular.io/) as a frontend framework
- [PrimeNG](https://primeng.org/) for ui-components
- [Bootstrap](https://getbootstrap.com/) as a grid-system
- [ChartJS](https://www.chartjs.org/) for diagrams

#### Backend
- [Spring Boot](https://spring.io/) as a backend-framework 
- [PostgreSQL](https://www.postgresql.org/) as a database
- [Gradle](https://gradle.org/) as a build tool
- [JWT](https://jwt.io/) for authentication

 
 ### Features
- Create and manage contacts
- Dashboard with Data-Visualization
- Create and mange deals

### Useful commands docker
```
docker compose up
docker compose down
docker compose -f docker-compose.dev.yml up -d
docker ps
docker build -t johannessiedersberger/springboot-crud-k8s:1.0 .
docker push johannessiedersberger/springboot-crud-k8s:1.0
```

### Useful commands kubectl
```
kubectl get services
kubectl delete service postgresql
kubectl delete service springboot-crud-svc
kubectl get deployments
kubectl delete deployment springboot-crud-deployment
kubectl delete deployment postgresql
kubectl create -f kubernetes/
kubectl delete -f kubernetes/
kubectl get pods
```

### Related Resources
- https://www.baeldung.com/spring-boot-minikube
- https://learnk8s.io/spring-boot-kubernetes-guide