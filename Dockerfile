FROM openjdk:21-jdk
ADD MockMarketBackend.jar MockMarketBackend.jar
ENTRYPOINT ["java","-jar", "/MockMarketBackend.jar"]