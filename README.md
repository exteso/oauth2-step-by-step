This example is based on the following resources:

 - http://projects.spring.io/spring-security-oauth/docs/oauth2.html
 - https://github.com/spring-guides/tut-spring-boot-oauth2/
 - https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2
 - https://spring.io/blog/2015/11/30/migrating-oauth2-apps-from-spring-boot-1-2-to-1-3



How to test:

1. $ `cd authorization-server;mvn spring-boot:run`
2. $ `cd resource-server;mvn spring-boot:run`

3. Obtain token with: $ `curl service-account-1:service-account-1-secret@localhost:8080/oauth/token -d grant_type=client_credentials`
4. Check the user endpoint with: $ `curl -H "Authorization: Bearer d1aeef2e-08b2-4d06-b665-1534ac2641b6" -v localhost:8080/user`
5. Access the resource with: $ `curl -H "Authorization: Bearer d1aeef2e-08b2-4d06-b665-1534ac2641b6" -v localhost:9090`
6. Update the resource with: $ `curl -H "Content-Type: application/json" -H "Authorization: Bearer d1aeef2e-08b2-4d06-b665-1534ac2641b6" -X POST -d "Bonjour" -v localhost:9090`
