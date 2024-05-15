package ChatRoom.Client;

import ChatRoom.Server.Server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Server server;
    private Client client;


    @BeforeEach
    void setUp() {
        server = new Server();

        Server.SocketServer clientHandler = new Server.SocketServer();

        Thread clientHandlerThread = new Thread(clientHandler);

        clientHandlerThread.start();

        client = new Client();
    }

    @AfterEach
    void tearDown() {
        client.closeConnection();
        server.stopServer();

    }



    // TODO: Change the greeting message from the server on established connection
    // Change the greeting string of the server and/or the unit test to a personalized greeting.
    @Test
    public void testGreetingMessage() throws IOException {
        // Arrange
        String expectedGreeting = "\"Welcome to our chat room!\"";

        // Act
        client.sendMessage(expectedGreeting);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedGreeting, receivedMessage);
    }




    // TODO: Implement a server reply to the client
    // Make the server reply to a message sent from the client. Change the message to reflect a different reply.
    @Test
    public void testReplyFromServer() throws IOException {
        // Arrange
        String expectedMessage = "Received your message. Thanks!";

        // Act
        client.sendMessage(expectedMessage);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedMessage, receivedMessage);
    }



    // TODO: Implement a method in the server that checks if an incoming message is a number.
    // If the message has numbers ONLY, the server should reply to the client with the square of the number in the message.
    @Test
    public void testSquareNumber() throws IOException {
        // Arrange
        String numberString = "8";
        String expectedResponse = "The square of 8 is 64.";

        // Act
        client.sendMessage(numberString);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }



    // TODO: Implement a method in the server that checks an incoming message from a client is a lowercase string.
    // If the message has lowercase letters only, the server should reverse the message and broadcast it to the client.
    @Test
    public void testStringReversal() throws IOException {
        // Arrange
        String message = "openai is awesome";
        String expectedResponse = "emosewa si ianaepo";

        // Act
        client.sendMessage(message);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }
}
