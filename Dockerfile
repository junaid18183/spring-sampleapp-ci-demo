FROM maven:3.8.6-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests

FROM junaid18183/jre1.8:2.0.0
COPY --from=build /workspace/target/*.war app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

HEALTHCHECK --interval=5s --timeout=3s --start-period=50s --retries=3 \
    CMD curl -s http://localhost:8080 || exit 1