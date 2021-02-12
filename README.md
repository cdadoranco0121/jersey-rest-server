# jersey-rest-server

### SQL db

```
CREATE DATABASE `rest_jersey_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `employe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `poste_occupe` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```

### cURL
```
curl --location --request GET http://localhost:9991/jersey/employes
curl --location --request GET http://localhost:9991/jersey/employes/list

curl --location --request POST 'http://localhost:9991/jersey/employes/add' \
--header 'Accept: application/xml' \
--header 'Content-Type: application/xml' \
--data-raw '<employe>
    <nom>Victor</nom>
    <prenom>Hugo</prenom>
    <posteOccupe>développeur</posteOccupe>
</employe>'
```




### XML to JSON ?

[https://stackoverflow.com/questions/1823264/quickest-way-to-convert-xml-to-json-in-java](https://stackoverflow.com/questions/1823264/quickest-way-to-convert-xml-to-json-in-java)