This example is based on the following resources:

 - http://stytex.de/blog/2016/02/01/spring-cloud-security-with-oauth2/
 - https://github.com/spring-cloud-samples/authserver/blob/master/src/main/java/demo/AuthserverApplication.java
 - http://projects.spring.io/spring-security-oauth/docs/oauth2.html
 - https://github.com/spring-guides/tut-spring-boot-oauth2/
 - https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2
 - https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2-vanilla
 - https://spring.io/blog/2015/11/30/migrating-oauth2-apps-from-spring-boot-1-2-to-1-3


How to test:

1. $ `cd authorization-server;mvn spring-boot:run`
2. $ `cd resource-server;mvn spring-boot:run`

3. Obtain token with: $ `curl service-account-1:service-account-1-secret@localhost:8080/auth/oauth/token -d grant_type=client_credentials` and save it in TOKEN=.......
4. Access the resource with: $ `curl -H "Authorization: Bearer $TOKEN" -v localhost:9090`
5. Update the resource with: $ `curl -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" -X POST -d "Bonjour" -v localhost:9090`

6. $ `cd webapp-server;mvn spring-boot:run`
7. go to localhost:9999 and use the UI :).

You can load the message from backend server, then submit a new one and try to reload.
All calls are using JWT, AS is called only the first time, RS checks the client has correct scopes.
If token expires it automatically goes to AS to get a new one.

For generating your own key (as written in the stytex.de blog):

`
keytool -genkeypair -alias jwt -keyalg RSA -dname "CN=jwt, L=Lugano, S=Lugano, C=CH" -keypass mySecretKey -keystore jwt.jks -storepass mySecretKey
`

copy jwt.jks in authorization-server/src/main/resources/jwk.jks

Notes:

 - Resource server fetch the pubkey of the authentication server, so in production it must be over a secure channel :)
 - If the authentication server is down, and a resource server is launched, the fetch of the public key will fail (but a log message will be written), see https://github.com/spring-projects/spring-security-oauth/issues/734 issue