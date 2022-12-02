spring:
  cloud:
    gateway:
      default-filters:
        - DedupeResponseHeader=Access-Control-Allow-Credentials Access-Control-Allow-Origin
      globalcors:
        corsConfigurations:
          '[/**]':
              allowedOrigins: "*"
              allowedMethods: "*"
              allowedHeaders: "*"
spring:
  cloud:
    gateway:
      routes:
        - id : r1
          uri : http://localhost:8082/
          predicates :
            - Path= /api/customers/**