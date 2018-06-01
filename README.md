# Project Winston
A microservice architecture project with Spring.


## Architecture

### [API Gateway](./api-gateway)

- Zuul Proxy

### [Discovery Service](./discovery-service)

- Eureka Server

### [Config Server](./config-service)

- Config Server

### Application Services

#### [Beer Service](./service-beer)

#### [Brew Service](./service-brew)

#### [User Service](./service-user)

#### [Review Service](./service-review)

#### [Sale Service](./service-sale)


## References

- [Master Microservices with Spring Boot and Spring Cloud - Udemy Course](https://www.udemy.com/microservices-with-spring-boot-and-spring-cloud)
- [Test Driven Development with Spring Boot - Sannidhi Jalukar, Madhura Bhave](https://content.pivotal.io/springone-platform-2017/test-driven-development-with-spring-boot-sannidhi-jalukar-madhura-bhave)

Technologies

- [Ribbon](https://spring.io/guides/gs/client-side-load-balancing/)
- [Zuul](https://spring.io/guides/gs/routing-and-filtering/)
- Eureka
- Cloud Config
- OpenFeign
- Lombok
- Web
  - MVC
  - WebFlux
- Data
  - JPA
  - MongoDB 