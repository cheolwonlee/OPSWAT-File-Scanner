# OPSWAT File Scanner
Java Spring Boot Application which takes in file(s) as input to scan for reports via the Metadefender Cloud API v4.

    1. Get Hash Value(s) of file(s)
    2. GET /hash/{hash} to see if hash of file is already analyized
        If not:
            2a. POST /file with multipart/form-data {file} -> dataId
            2b. GET /file/{dataId} from 2a and keep poking to see if scan is complete
    3. Output the results of analysis

#### Dependencies
- Java 1.8
- Maven 3.8.2

### How to build
> mvn clean install

### How to run
> java -jar -Dopswat.api.key=<API_KEY> OpswatFileScanner-1.0.jar <Directory/File_Name>

### Notes:
    - File Hash algorithm is configurable (SHA-256 [Default], SHA-1, MD5) with -Dopswat.hash.algo=<ALGO>
    - Base Url can be configurable (https://api.metadefender.com/v4 [Default]) with -Dopswat.api.url.base=<BASE_URL>