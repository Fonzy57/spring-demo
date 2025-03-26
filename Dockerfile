FROM ubuntu:latest
LABEL authors="Steph"

# Update les dépendances et installe jdk 21
RUN apt-get update
RUN apt-get install -y openjdk-21-jdk-headless

# Créer un dossier /opt/app
RUN mkdir /opt/app

#Copie le fichier de notre machine locale vers /opt/app/myappjar
COPY target/cda_demo-0.0.1-SNAPSHOT.jar /opt/app/myappjar

# Signale à docker quel est le dossier de travail
WORKDIR /opt/app

# Démarre l'application
ENTRYPOINT ["java", "-jar", "myapp.jar"]