

Steps to run server :-

1. git clone git@github.com:nikhilninawe/sensor-monitor.git
2. cd sensor-monitor/
3. ./gradlew clean build
4. java -jar build/libs/sensor-monitor-0.1.0.jar 

This will start the http server, which is listening on port 8090
There are 2 main services within the server

1. SensorService : This service registers clients for the sensor of their interest. Secondly, the server also publishes sensor updates to clients.
2. HeartbeatService : This component periodically checks the client for connectivity. If the server is unable to connect to client for 2 attempts, the client is removed for pushing any updates

SensorUpdates component in the server generates random temperature updates and makes REST call to server. This component can separated out but for simplicity made this component part of the server.


Steps to run client :-

1. git clone git@github.com:nikhilninawe/sensor-client.git 
2. cd sensor-client/
3. ./gradlew clean build
4. java -Dserver.port=8095 -Dsensor=S1 -jar build/libs/sensor-client-0.1.0.jar

In the final step, we can modify the port and sensor id.
We can run multiple clients using step 4 with different port and same/different sensor id.


Features that can be added for a customer who wants to monitor the temperature of his or her storage room

1. Statistics about sensor temperature like min, max, average.
2. When was the lowest/highest temperature recorded
3. Trigger an action when a predefined threshold temperature is reached

