# **CHALLENGE MELI**

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men. Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN. En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

## ENVIRONMENT
- JAVA 8
- MAVEN
- INTELLIJ
- POSTMAN

## CONSIDERACIONES
- Se realizaron unit test para las clases core, generando un reporte con el plugin JACOCO, alcanzando un coverage de: 85%

<img width="1436" alt="image" src="https://user-images.githubusercontent.com/93158294/173249818-1f166d06-d002-4f04-a13f-fd783ba59566.png">

- Se utilizo una base de datos relacional (MySql) para la persistencia de información (Solo es posible reallizar 5 conexiones simultaneas a la base de datos)
- El servicio API REST se encuentra expuesto en AWS [HOST](Dnaanalyzer-env-1.eba-jdmphxbn.us-east-1.elasticbeanstalk.com)
- La API REST se desarrollo en spring boot con una arquitectura hexagonal.

## INSTRUCCIONES DE USO
- Detectar si un humano es mutante enviando la secuencia de ADN
  - Metodo POST
  - Body: JSON
  - Ejemplo del body:
    ```
    {
      "dna": ["ATGCGT","CAGGCC","TTAGGT","AGGAGG","ACCCTA","TCACCG"]
    }
    ``` 
  - Respuestas:
    - 200: Mutante identificado
    - 403: Humano identificado
    - 400: La matriz no es cuadrada
    - 400: Carácteres no válidos en la secuencia de ADN
    - 400: Matriz de tamaño menor, al tamaño de la secuencia que se debe identificar
  - Endpoint [Dnaanalyzer-env-1.eba-jdmphxbn.us-east-1.elasticbeanstalk.com/mutant/](Dnaanalyzer-env-1.eba-jdmphxbn.us-east-1.elasticbeanstalk.com/mutant/)


- Estadísticas de las verificaciones de ADN
  - Metodo GET
  - Respuestas:
    - 200: Estadisticas del servicio
    - 400: Error hacía la base de datos (Conexión o consulta)
  - Endpoint [Dnaanalyzer-env-1.eba-jdmphxbn.us-east-1.elasticbeanstalk.com/stats](Dnaanalyzer-env-1.eba-jdmphxbn.us-east-1.elasticbeanstalk.com/stats)
