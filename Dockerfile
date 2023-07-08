FROM maven:3.6.3-jdk-11-slim AS builder
COPY . .
RUN mvn clean install

# Copy the jar and test scenarios into our final image
FROM openjdk:17-slim
COPY --from=builder target/app.jar .
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod -R 777 /usr/local/bin/entrypoint.sh
EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]