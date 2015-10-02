Testing REST-API from Windows Powershell:

1) Create structures
C:\Users\yvesgross.BBV> $book1 = @{id=0; iban='111111'; title='The Book 1'; author='Tom Garret'} | ConvertTo-Json
C:\Users\yvesgross.BBV> $book2 = @{id=0; iban='222222'; title='The Book 2'; author='Sue Hayley'} | ConvertTo-Json
C:\Users\yvesgross.BBV> $book2 = @{id=0; iban='333333'; title='The Book 3'; author='Mark Mayweather'} | ConvertTo-Json

2) Structure to JSON
C:\Users\yvesgross.BBV> $json = $book1 | ConvertTo-Json
or
C:\Users\yvesgross.BBV> $json = ConvertTo-Json($book1)

3) Variable to shortcut the URI
C:\Users\yvesgross.BBV> $uri = 'http://localhost:8080/my-javaee7-rest-api/api/books'

4) Calling the REST-API
GET:
C:\Users\yvesgross.BBV> Invoke-RestMethod -Uri $uri -Method Get | ConvertTo-Json
or shorter:
C:\Users\yvesgross.BBV> irm -Uri $uri -Method Get | ConvertTo-Json

POST:
C:\Users\yvesgross.BBV> Invoke-RestMethod -ContentType 'application/json' -Uri $uri -Method Post -Body $json

PUT:
C:\Users\yvesgross.BBV> irm -ContentType 'application/json' -Uri $uri/cd449994-4897-46f6-84d0-329112b8b5a3 -Method Put -Body $book1a

DELETE:
C:\Users\yvesgross.BBV> irm -ContentType 'application/json' -Uri $uri/cd449994-4897-46f6-84d0-329112b8b5a3 -Method Delete