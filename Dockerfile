FROM java:8
ADD target/cheeringvote-0.0.1-SNAPSHOT.jar CheeringVote.jar
RUN sh -c 'touch /CheeringVote.jar'
EXPOSE 80
ENTRYPOINT ["java","-jar","/CheeringVote.jar"]