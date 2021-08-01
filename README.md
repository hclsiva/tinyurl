# tinyurl
System Design Study for tinyurl

Added gcloud kubernetes

## Say Hello

Method : Get

URL : http://localhost:8085/greeting/sayHello?name=Sivashankar

## Health Check

Method : Get

URL : http://localhost:8085/tinyurl/health

## Find all URLs

Method : Get

URL : http://localhost:8085/tinyurl/getAllUrls

## Find URL for a given Hash

Method : Get

URL : http://localhost:8085/tinyurl/getUrl?hash=7b081b13


## Single url Insert

Method : Post

URL :http://localhost:8085/tinyurl/insertUrl

Request Body

    { 
        "url":"www.github.com/hclsiva" 
    }

## Bulk Insert

Method : Post

URL : http://localhost:8085/tinyurl/bulkInsert

- Inserts 1 million entries for the url https://www.google.co.in/search?q=$n. where $n is from the count 1 to 1 Million
