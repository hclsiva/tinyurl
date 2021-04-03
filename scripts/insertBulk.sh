#!/bin/bash
curl -v -X POST http://localhost:8080/tinyurl/bulkInsert -H "Content-Type: application/json" -H "Accept: application/json"