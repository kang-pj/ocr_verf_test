# Refine Application

Spring Boot 기반의 웹 애플리케이션입니다.

## 프로젝트 구조

- **src/main/java/com/refine**: 메인 애플리케이션 소스
  - **aop**: AOP 관련 클래스
  - **common**: 공통 기능 (코드, 인증, XSS 방지 등)
  - **config**: 설정 클래스
  - **filter**: 필터 클래스
  - **login**: 로그인 관련 기능
  - **ocr**: OCR 처리 기능
  - **security**: 보안 설정
  - **util**: 유틸리티 클래스
  - **worker**: 워커 관련 기능

- **src/main/webapp**: 웹 리소스
  - **WEB-INF/views**: JSP 뷰 파일
  - **resources**: CSS, JS, 이미지 등 정적 리소스

## 실행 방법

```bash
./gradlew bootRun
```

## 빌드 방법

```bash
./gradlew build
```