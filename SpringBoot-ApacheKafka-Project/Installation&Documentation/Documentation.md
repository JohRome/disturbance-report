# Dokumentation: Johan's Disturbance Reporter

### Arbete utfört av
+ Johan Romeo - JIN23

### Beskrivning av projektet
I sin helhet låter applikationen användaren:
+ Fylla i en störningsrapport om något som har hänt i fastigheten, t.ex störande ljud ifrån grannar sent på kvällen.
+ Störningsrapporten skickas sedan med hjälp av en Kafka Producer, mot ett web API.
+ En Kafka Consumer lyssnar på det som skickas, kontrollerar så att innehållet är bra, och skickar sedan vidare ärendet in i en MongoDB.
+ En Kafka Consumer lyssnar på det som skickas och skriver ut innehållet till modulen som skickar störningsrapporten.
+ Detta ärende får ett MongoID och en boolean, IsSolved, som automatiskt är satt till false, eftersom ärendet först måste åtgärdas innan det kan sättas till true.
## Arbetet och dess genomförande
Arbetet började med att förstå konceptet i hur man använder Apache Kafka som integrationsplattform och jag använde mig då av [den här spellistan](https://www.youtube.com/playlist?list=PLGRDMO4rOGcNLwoack4ZiTyewUcF6y6BU).  
Jag valde sedan att komma på en idé om hur jag ville forma mitt egna program - Jag kom på vad för typ av data jag vill skicka, hur denna data ska skickas och behandlas och även vilken databas som ska lagra datan.
### Vad som varit svårt
Själva implementationen i applikationen har inte varit så värst komplicerad, utan det svåraste har varit strukturen i projektet. En felplacerad klass i en modul kan ha förödande konsekvenser och göra programmet helt okörbart. Därför är det av största vikt att ha tydliga ansvarsområden för varje enskild modul, så att de ej "krockar" med en annan modul.
Något som var besvärligt till en början var även att låta en modul ha en annan modul som en dependency.
### Beskriv lite olika lösningar du gjort
+ **KafkaConfig-modulen**
  + TopicConfig - *Med hjälp av replicas och partitions ökar säkerheten och skalbarheten i programmet. Om en Broker t.ex försvinner så finns det möjlighet att en annan Broker tar över ser till programmet inte kraschar*
+ **KafkaMongoConsumer-modulen**
  + ReportEntity - *Skapar ett dokument med namnet "reports" i MongoDB*
  + MongoConsumer - *Lyssnar på en angiven topic och hämtar data och skickar in den i MongoDB, förutsatt att vissa kriterier stämmer överens tack vare en kontroll-metod som säkerställer att fält är ifyllda och ej tomma*
  + ReportRepository - *Gör det möjligt för MongoConsumer att enkelt spara data i MongoDB*
+ **KafkaProducer-modulen**
  + KafkaProducer - *Ansvarar för att skicka data till en specifik Topic med hjälp av en MessageBuilder. Ansvarar även för att fånga upp eventuella fel så att program inte kraschar*
  + RestController - *Sätter upp ett API med en endpoint som gör det möjligt att låta användaren skicka data med en POST-request*
+ **PostToAPI-modulen**
  + ApacheKafkaAPI - *Här utförs själva HTTP POST-request och ser till att data som ska skickas är i JSON-format*
  + Application - *Gör så att applikationen kan loopas och låter även användaren fylla i själva störningsrapporten*
  + ConsoleConsumer - *Skriver ut den skickade data ut i konsolen*
  + ReportDTO - *Skapar en mall för hur data ska se ut när den skickas till API:et*
  + Sender(Interface) - *Gör det möjligt för klasser som ärver dessa metoder att implementera dem hur de vill och behöver. Detta gör det möjligt att ha olika implementationer när det kommer till att utföra POST-request samt att göra om data till JSON-format. Detta kan vara användbart när man t.ex vill byta http-client, bibliotek för JSON, eller viljan att skicka mot en annan integrationsplattform*
  + Serialized(Interface) - *När DTO-klasser implementerar detta interface ges möjligheten för ApacheKafkaAPI att kunna ta emot en Serialized som inparameter, istället för varje enskild DTO. I skrivande stund är ReportDTO den enda klassen, men om det hade funnits fler klasser så slipper man att skriva en metod för varje klass*
  + Input - *Ansvarar för att se till att data som matas in är korrekt så att det förhindrar problem vidare i programmet*
  + Output - *Skriver ut menyalternativ för användaren samt meddelar om input är ej korrekt*

### Beskriv något som var besvärligt att få till

### Beskriv om du fått byta lösning och varför i sådana fall


## Slutsatser

### Vad gick bra

### Vad gick dåligt

### Vad har du lärt dig

### Vad hade ni gjort annorlunda om ni gjort om projektet

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
