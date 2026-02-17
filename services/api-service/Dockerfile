# 1. Base image: use official OpenJDK 17 runtime image
FROM eclipse-temurin:17-jre-jammy

# 2. Image metadata
LABEL description="Dockerized online ordering system." \
      maintainer="lucas.wang.1024@gmail.com"

# 3. Build arguments and environment variables for flexible configuration
ARG SERVER_TYPE=test
ENV APP_DIR=/app/os/api-service \
    APP_PORT=8041

# 4. Install required utilities (curl for HEALTHCHECK), then clean up apt cache
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

# 5. Create application and log directories
RUN mkdir -p ${APP_DIR}/log

# 6. Set working directory
WORKDIR ${APP_DIR}

# 7. Copy application JAR into the image
COPY target/api-service-1.0.0-SNAPSHOT.war .

# 8. Declare volume for log persistence
VOLUME ${APP_DIR}/log

# 9. Expose application port
EXPOSE 8041

# 10. Health check (use localhost inside the container; avoid hard-coded IP)
HEALTHCHECK --start-period=60s --interval=180s --timeout=10s --retries=3 \
    CMD curl -fsS "http://localhost:${APP_PORT}/actuator/info" || exit 1

# 11. Container startup command
CMD ["java", \
     "-Xms64m", \
     "-Xmx128m", \
     "-Dfile.encoding=utf-8", \
     "-jar", \
     "api-service-1.0.0-SNAPSHOT.war"]
