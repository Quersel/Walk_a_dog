# Walk-A-Dog BE

Walk-A-Dog Backend

## How to run it in a docker container?

We are using [Cloud Native Buildpacks](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/htmlsingle/#build-image) included in recent version of Spring Boot, so there is no dedicated Dockerfile. 


0. Ensure that a `.env` file exist - you should copy `.env.sample` to `.env` and change password inside.
1. Build a docker image:
```bash
mvn spring-boot:build-image
```
2. Then start docker compose:
```bash
docker-compose up
```

It will run a docker profile which is based on _non-persistent_ postgres database.
