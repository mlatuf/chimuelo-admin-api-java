FROM gcr.io/distroless/java:11
WORKDIR /app
COPY target/*.jar ./app.jar
EXPOSE 8080 8080
CMD ["app.jar"]