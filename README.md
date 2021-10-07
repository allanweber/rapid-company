# Rapid Company

## Docker

docker network create rapid-network

* **Build**: `docker build -t allanweber/company:latest -f docker/Dockerfile .`

* **Run Locally**: `docker run -p 8081:8081 --net=rapid-network -e SPRING_PROFILES_ACTIVE=local --name rapid-company allanweber/company:latest`

* **Run from repo**: `docker run -p 8081:8081 -d --name rapid-company allanweber/rapid-company:latest`