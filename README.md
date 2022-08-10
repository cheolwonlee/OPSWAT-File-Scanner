# OPSWAT File Scanner
Java Spring Boot Application which takes in file(s) as input to scan for reports via the Metadefender Cloud API v4

#### Dependencies
- Java 1.8
- Maven 3.8.2

### How to build
> mvn clean install

### How to run
> java -jar -Dopswat.api.key=<API_KEY> OpswatFileScanner-1.0.jar <Directory/File_Name>