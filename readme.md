# **Installatiehandleiding voor Ontwikkelaars**
Dit installatiehandleiding is specifiek opgesteld voor ontwikkelaars. Het installatiehandleiding voor de product owner, met instructies voor installatie met een server, is te vinden onder -> (https://confluenceoosevt.aimsites.nl/pages/viewpage.action?pageId=296340854)

### **1. Frontend runnen**
1\. Open een terminal binnen of buiten de IDE en ga hierbinnen naar de map 'skilltree-webapp'.

2\. Typ een van de volgende lines in om alle dependencies te installeren:

npm i / npm install

3\. Typ vervolgens dit in om de frontend te starten:

npm start

4\. De browser wordt automatisch geopend en <http://localhost:3000/> wordt laten zien. Dit is het lege skill network overzicht.
### **2. Backend runnen**
De frontend van Skilltree kan worden opgestart aan de hand van een lokale Wildfly server. Zorg ervoor dat er een skilltree database bestand bestaat. Een Maven project is worden alle benodigde plugins automatisch geïnstalleerd zodra je het project build, dus hier hoef je je geen zorgen over te maken. De onderstaande gids is voor de IDE IntelliJ IDEA. Als je een andere IDE gebruikt, zal dit proces misschien iets anders zijn.

1\. Klik rechtsboven op 'Add Configuration' en vervolgens op 'Add new'.

2\. Selecteer 'JBos/Wildfly Server - Local'.

3\. Kies binnen het kopje 'Server' bij 'Application server' de eerder geïnstalleerde versie van JBoss/WildFly 25.0.0.Final.

4\. Kies binnen het kopje 'Server' bij 'JRE' de eerder geïnstalleerde versie van Java versie 17.

5\. Klik binnen het kopje 'Deployment' op het plustekentje, klik op 'Artifact' en kies vervolgens voor 'Java-REST-API:war'.

6\. Verander binnen het kopje 'Deployment' de tekst bij 'Application context' van '/skillnetwork\_api\_war' naar '/'.

7\. Klik op 'Apply' en vervolgens op 'OK'.

8\. In de terminal binnen Java-REST-API map schrijf de volgende zin 'mvn install'.

9\. Klik vervolgens rechtsboven op het groene run-icoontje om de backend te starten.

10\. Er zal een browserpagina geopend worden met de boodschap dat de database runt.
### **3. Runnen van de testen**
Er zijn bij skilltree alleen unittesten geschreven binnen de backend. Deze zijn via de terminal te runnen aan de hand van het volgende commando:

mvn test


