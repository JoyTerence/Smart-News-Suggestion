# Smart-News-Suggestion
Gives relevant article suggestion using a photo of a news paper article.

TextBlockRecognition project contains two Java files<br><br>
1.<i>TextBlock.java</i> -> takes photo of a newspaper article as input and gives an output rectangle which bound the headlines in the photo.<br>
2.<i>OCR.java </i>      -> takes this bounding rectangle and determines the characters in it so that the headlines are obtained as a String Object in Java.

Webscraper project too contains two Java files.<br><br>
1.<i>Webscrape.java</i>       -> takes the String output containing the headline and using Jaunt library extracts relevant Articles (Currently from <B>THE HINDU</B>, <B>INDIA TIMES</B> and <B>HINDUSTAN TIMES</B>) from their websites.<br>
2.<i>DisplayArticle.java</i>  -> takes the above string output and uses Swing to give a output UI for viewing relevant news.

<br><br>

Project was run in Eclipse with OPENCV library version - 3.0.0.
Inputs were photos of newspaper taken from mobile.

