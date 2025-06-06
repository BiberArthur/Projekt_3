# QA_API_Tests

Dieses Projekt enthält automatisierte Tests für eine RESTful API, die Benutzer- und Auftragsendpunkte verwaltet. Die Tests decken sowohl positive als auch negative Szenarien ab und überprüfen die Funktionalität der wichtigsten HTTP-Methoden (GET, POST, PUT, DELETE).  
Ziel ist es, die Zuverlässigkeit und Stabilität der API durch systematische Testfälle sicherzustellen. Die Tests wurden mit Java, JUnit und Rest Assured umgesetzt und verwenden Java Faker zur Generierung realistischer Testdaten.  
Das Projekt ist modular aufgebaut und ermöglicht eine einfache Erweiterung um neue Testfälle oder Endpunkte. Es eignet sich sowohl für funktionale Tests während der Entwicklung als auch für Regressionstests in späteren Phasen.

## Technologien

| Technologie    | Version   |
|----------------|-----------|
| Java           | 11        |
| Maven          | 3.9.0     |
| JUnit          | 4.13.2    |
| Rest Assured   | 4.4.0     |
| Lombok         | 1.18.24   |
| Java Faker     | 1.0.2     |
| Allure         | 2.21.0    |

## Voraussetzungen

1. Java JDK 11 installieren: [Java JDK 11 Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2. Maven 3.9.0 installieren: [Maven Download](https://maven.apache.org/download.cgi)
3. Repository auf die lokale Maschine klonen:

```bash
git clone https://github.com/BiberArthur/Projekt_2.git
```
4. Abhängigkeiten installieren (falls Maven genutzt wird)**:

  - Das Projekt benutzt Maven. Stellen Sie sicher, dass Maven installiert ist und führen Sie:
```bash 
mvn clean install
```
## Tests ausführen
Die Tests können über Maven ausgeführt werden:
```bash
mvn test
```
Für einen Allure-Bericht:
```bash
mvn allure:serve
```
## Testberichte

Testframework: JUnit
Testausführung: Maven
Berichterstellung: Allure Reports





