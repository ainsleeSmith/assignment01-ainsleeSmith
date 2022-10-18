# assignment01-ainsleeSmith

## Project Description

This project takes a takes a user inputted actor name and using the command line inputted file([tmbd_5000_credits](https://drive.google.com/file/d/1byQ1PmbydVO49niqrlYEbvbpGtxgBzPX/view?usp=sharing)), searches if the actor is in the listed movies. If actor is found in the file it displays a movie wall of all the movies the actor has been in, as well as the characters they played in the movie. If actor is not found in file, it suggests the next closest actor alphabetically to what the user inputs. If the user says agrees with the suggestion it then displays that actor's movie wall, and if they do not agree it tells them that the actor was not found and asks them to input another name. This cycle continues until the user inputs the words "EXIT" into the inputting prompt. 
## Dependencies Used

I used the CSV Open library, specifically CSVFormat, CSVParser, and CSVRecord. To install them I first changed my java project to a maven project which allowed me to make a pom.xml file. Inside of the pom.xml file I included these CSV Open library dependencies by writing: 
```
<dependency>
	 <groupId>org.apache.commons</groupId>
	 <artifactId>commons-csv</artifactId>
	 <version>1.6</version>
</dependency>
```
From there I was able to import them into my code by writing:
```
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
```

