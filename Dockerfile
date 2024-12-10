# Use a multi-stage build to reduce the final image size

# Stage 1: Build
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy build files
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
RUN chmod +x gradlew

# Download dependencies
RUN ./gradlew build --no-daemon --stacktrace -x test

# Copy source files
COPY src ./src

# Build the application
RUN ./gradlew bootJar --no-daemon --stacktrace

# Stage 2: Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Add a volume for logs (optional, depending on the Docker Compose setup)
VOLUME /var/log/online-bookstore

# Set environment variables
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
