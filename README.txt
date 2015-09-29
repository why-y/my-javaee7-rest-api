Testing REST-API from Windows Powershell:

1) Create structures
C:\Users\yvesgross.BBV> $book1 = @{iban='111111'; title='The Book 1'; author='Tom Garret'}
C:\Users\yvesgross.BBV> $book2 = @{iban='222222'; title='The Book 2'; author='Sue Hayley'}
C:\Users\yvesgross.BBV> $book2 = @{iban='333333'; title='The Book 3'; author='Mark Mayweather'}

2) Structure to JSON
C:\Users\yvesgross.BBV> $json = $book1 | ConvertTo-Json
or
C:\Users\yvesgross.BBV> $json = ConvertTo-Json($book1)

3) Variable to shortcut the URI
C:\Users\yvesgross.BBV> $uri = 'http://localhost:8080/myJavaEE7Project1/api/books'

4) Calling the REST-API
GET:
C:\Users\yvesgross.BBV> Invoke-RestMethod -Uri $uri -Method Get
POST:
C:\Users\yvesgross.BBV> Invoke-RestMethod -ContentType 'application/json' -Uri $uri -Method Post -Body $json
