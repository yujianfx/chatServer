FROM wangudiercai/oracle-jdk8:latest
LABEL maintainer="2653084650@qq.com"
ENV JAVA_VERSION="8"
COPY target/*.jar /app.jar
RUN  apk add -U tzdata; \
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime; \
echo 'Asia/Shanghai' >/etc/timezone; \
touch /app.jar;
EXPOSE 10800
ENTRYPOINT [ "sh", "-c", "java  -jar /app.jar" ]