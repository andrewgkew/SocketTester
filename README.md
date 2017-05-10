# Socket Tester
This project can be used to test sending both TCP and UDP packets across a network.

Currently there are 3 types of socket testers

1. TCP socket tester
2. UDP socket tester
3. UDP ping pong server

The project is a maven configured Java project which when packaged will produce 6 separate jars for each type of tester

A client jar and a server jar. Each tester runs using both client and server to run and takes different input parameters to run

## Getting Started
The best way or easiest way to use this project is to clone the repository to your local machine, then package it using maven and run the jars as described below, or create application configuration in your IDE to run it in the IDE, this is the easiest way to debug the code to see whats going on in the project if there are issues.


## Running the application
### Testers input parameters

## 1. TCP socket tester
The TCP socket tester is made up of 2 executable jars. You should be running both the client and server jars together. Start the server first to create the socket and then start the client after. This socket tester is a simple knock knock server, where the client can input a response to server response in the form of answering a knock knock joke.
  ###a. TCP Server
      
    This executable takes 1 input parameter
    1. port number to create a server socket to listen on
      
    Usage: java -jar SocketTester-server.jar <port number>
    
  ###b. TCP Client
    
    This executable takes 2 input parameters
    1. hostname the Server jar is running on
    2. port number the server is listening on
    
    Usage: java -jar SocketTester-client.jar <host name> <port number>

## 2. UDP socket tester
This UDP socket tester is slightly different to the TCP tester in that you first start the UDP server on a machine on the network, then when you start the UDP client on another machine it makes a UDP request to the server and then server will return a quote from the one-liners.txt file located in the resources directory of this project. The easiest way to run this if you are using the executable jars is to place the one-liners.txt file in the same location as the jar and then set the system parameter to that directory (without the last forward slash and name of the file)

If you do not set the inputFilePath system parameter it will instead return the current system date so you can run this without that file as well.
  ###a. UDP Server
    
    This executable takes 1 input parameter
    1. port number to create a server socket to listen on
    
    And 1 system parameter
    1. -DinputFilePath which is the absolute path to the file one-liners.txt
    
    Usage: java -DinputFilePath=/path/to/one-liners-file -jar SocketTester-udpServer.jar <port>
      
  ###b. UDP Client
    
    This executable takes 2 input parameters
    1. hostname the Server jar is running on
    2. port number the server is listening on
    
    Usage: java -jar SocketTester-udpClient <hostname> <port number>
  


## 3. UDP ping pong tester
This UDP ping pong server works in a similar way to the standard UDP socket tester.

Firstly the client sends a UDP packet with some data in it and then sits and waits for a response which is a blocking step, the UDP server receives that data, logs it to the console adds some additional data to it so that we know its been through the server and then sends it back to the client. The client then receives this response from the server writes the data of the packet to the console. That ends 1 iteration of a ping pong chat.

The 3rd input parameter of the client will then repeat that exact ping pong chat to the value of the count input parameter
  
  ###a. UDP ping pong Server
      
    This executable takes 1 input parameter
    1. port number to listen on
      
    Usage: java -jar SocketTester-udpPingPongServer.jar <port>
    
  ###b. UDP ping pong Client
    
    This executable takes 3 input parameters
    1. hostname of the ping pong server
    2. port number - the port to send UDP packet on and listen for a response
    3. count - number of messages to send
    
    Usage: java -jar SocketTester-udpPingPong <hostname> <port> <count>
    
##Contributing
Please refer to each project's style guidelines and guidelines for submitting patches and additions. In general, we follow the "fork-and-pull" Git workflow.

 1. **Fork** the repo on GitHub
 2. **Clone** the project to your own machine
 3. **Commit** changes to your own branch
 4. **Push** your work back up to your fork
 5. Submit a **Pull request** so that we can review your changes

NOTE: Be sure to merge the latest from "upstream" before making a pull request!

## Authors

* **Andrew Kew** - *[Applied Financial Technology](http://www.appliedfinancialtechnology.com/)* - [GitHub](https://github.com/andrewkew-aft)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Oracle tutorial on creating a Datagram Client and Server - [link](https://docs.oracle.com/javase/tutorial/networking/datagrams/clientServer.html)
