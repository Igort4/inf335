FROM openjdk:11
COPY Ola.java

RUN javac Ola.java

CMD ["java", "-classpath", "", "Ola"]
