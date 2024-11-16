# 빌드 스테이지
FROM openjdk:17 AS builder

# Gradle 파일 먼저 복사 (의존성 캐시 최적화)
COPY settings.gradle build.gradle gradlew ./
COPY gradle gradle
RUN chmod +x ./gradlew
RUN ./gradlew dependencies

# 전체 소스 코드 복사 후 빌드
COPY . .
RUN ./gradlew bootJar	# gradlew를 통해 실행 가능한 jar파일 생성

# 런파임 스테이지
FROM openjdk:17
COPY --from=builder build/libs/*.jar app.jar

# 애플리케이션 포트 열기
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]