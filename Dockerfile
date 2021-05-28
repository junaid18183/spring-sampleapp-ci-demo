FROM alpine:3.11

ARG BUILD_DATE
ARG BUILD_URL
ARG GIT_SHA

LABEL com.ijuned.devops.build-date=${BUILD_DATE} \
      com.ijuned.devops.build-url=${BUILD_URL} \
      com.ijuned.devops.name="myapp" \
      com.ijuned.devops.description="docker image for sampleapp" \
      com.ijuned.devops.url="https://devops.ijuned.com" \
      com.ijuned.devops.vcs-ref=${GIT_SHA} \
      com.ijuned.devops.vendor="Juvensys" \
      com.ijuned.devops.schema-version="1.0"
