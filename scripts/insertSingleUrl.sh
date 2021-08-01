#!/bin/bash
curl -v -X POST http://localhost:8085/tinyurl/insertUrl -H "Content-Type: application/json" -H "Accept: application/json" -d @insertSingleUrlInput.json