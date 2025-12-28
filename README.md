Setup guide:
1. Download cosmos-odyssey.jar from https://github.com/andrusmuur/cosmos-odyssey/releases
2. Empty H2 database file is generated when running the program. If you want a database with some values in it, you can download it from https://drive.google.com/file/d/1bBpfWoEOTbEV4ydh7MwCLCbIUJihoFEA/view?usp=sharing. Add this file to the same directory as the jar file.
3. Run the program with 'java -jar cosmos-odyssey.jar', requires JDK 25 to be installed
4. Open http://localhost:8080/
5. You can open H2 console at http://localhost:8080/h2-console/. JDBC URL: jdbc:h2:file:./CosmosOdysseyDB, user name and password empty.