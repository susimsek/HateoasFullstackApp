FROM ghcr.io/graalvm/graalvm-ce:ol7-java17

ENV MAVEN_VERSION=3.8.3
ENV DOCKER_VERSION=20.10.9
USER root

# native image
RUN gu install native-image

# Maven
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# Docker
RUN curl -fsSL https://download.docker.com/linux/static/stable/x86_64/docker-$DOCKER_VERSION.tgz | \
  tar zxvf - --strip 1 -C /usr/bin docker/docker


ENV M2_HOME /usr/share/maven
ENV maven.home $M2_HOME
ENV M2 $M2_HOME/bin
ENV PATH $M2:$PATH

# Again using non-root user i.e. stakater as set in base image
USER 10001

RUN java -version

RUN mvn --version

RUN native-image --version
