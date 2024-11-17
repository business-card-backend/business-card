# 빌드 스테이지
FROM openjdk:17-jdk-slim AS builder

# 전체 소스 코드 복사 후 빌드
COPY . .
RUN rm -rf business-card-config
RUN chmod +x ./gradlew
RUN ./gradlew bootJar	# gradlew를 통해 실행 가능한 jar파일 생성

# 런타임 스테이지
FROM openjdk:17
COPY --from=builder build/libs/*.jar app.jar

# 애플리케이션 포트 열기
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=dev"]
