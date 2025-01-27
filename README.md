# nwassignment

Prerequisites:

Maven 3.4.2+
Java 17+

Booting Up the Application:

	1. Clone the repository:
	git clone https://github.com/tanto2/nwassignment
	2. Navigate to the folder:
	cd nwassignment
	3. Run the application:
  mvn clean install

Testing:

1. Create article
   
• URL:  http://127.0.0.1:8080/article

• HTTP Method: POST

• Body
{
    "url": "http://www.rte.ie/news/politics/2018/1004/1001034-cso/",
    "engagement": 20,
    "location": "ie" 
}

{
"url": "https://www.irishtimes.com/opinion/cartoon/2025/martyn-turner/",
"engagement": 50,
"location": "ie"
}


{
"url":"https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/",
"engagement": 60,
"location": "ie"
}


{
"url": "https://elpais.com/cultura/festival-de-musica-de-canarias/",
"engagement": 10,
"location": "es"
}

Result:
<img width="1147" alt="image" src="https://github.com/user-attachments/assets/1d29e7e5-55c7-4cfe-b87e-b724f4661662" />


2. Given a country, provide the aggregated social score for the domains in the system ranked by social score

• URL:  http://127.0.0.1:8080/report/{location}

	Ex: localhost:8080/report/ie
 
• HTTP Method: GET

Result:
<img width="1147" alt="image" src="https://github.com/user-attachments/assets/a08012d4-1a5b-46dc-adcd-f3f3be0b32a2" />



3. Delete article based on url
   
• URL:  http://127.0.0.1:8080/article?url={url}

	• Ex: localhost:8080/article?url=http%3A%2F%2Fwww.rte.ie%2Fnews%2Fpolitics%2F2018%2F1004%2F1001034-cso%2F
 
• HTTP Method: DELETE

Result:
<img width="1147" alt="image" src="https://github.com/user-attachments/assets/95ee6739-e8ae-46aa-8528-50a3f79092c6" />


