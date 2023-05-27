# SwingsGuiChatbot


Welcome to the Chat Application! This is a simple chatbot application built using Java Swing. It allows users to interact with a chatbot, send messages, and receive replies.
The Discord chatbot extension is currently in development and the source code is avaialaible in the following link [https://github.com/kiaririka/HealthDiscordBotMaven] will be deployed soon.


## Features

 1. User-friendly graphical user interface (GUI) built with Java Swing.
 2.  Chat area to display messages between the user and the chatbot.
 3. Input field for users to enter messages and send them to the
    chatbot. 
 4. Messages are displayed in a conversation-like format, with
    a sender label for each message. 
 5. The chatbot responds to specific
        commands such as /about, /help, and /talk.
 6. Automatic scrolling to
        the bottom of the chat window when new messages are rendered

## Getting Started

  To run the Chat Application locally on your machine, follow these steps:
  
 1. git clone this rpositry.
 2. Open the project in your preferred Java IDE.
 3. Build the project and resolve any dependencies (preferred maven with intellij) 
 4. It uses JDK,swings library and JSON for parsing the messages. Be sure to add the dependencies if facing any error.
 5. Run the ChatApplication class.
 6. The Chat Application GUI will open, and you can start interacting with the chatbot.
 7. Once the application starts, be a little patient as it takes some time for Bard to fetch the responses for reply.  
  

## Usage

Enter your message in the input field at the bottom of the application.
Press the "Send" button or press Enter to send the message.
The chatbot will process your message and provide a response.
You can use the following commands:

 1. /about: Get information about the chatbot.
 2. /help: Display a list of available commands.

## Customization

Feel free to customize and enhance the Chat Application to fit your needs. Here are a few ideas:

 1. Implement additional chatbot commands and functionalities.
 2. Improve the user interface by adding icons, colors, or themes.
 3. Connect the chatbot to external APIs or databases for more advanced
    interactions. Implement data persistence to save chat histories or
    user preferences.

## API Integration

The chatbot uses a custom Google Bard API to generate responses. The API integration is handled in the PostRequest class.


## **Project Structure**

The project consists of the following files:

 1. PostRequest.java: Contains the code for making HTTP POST requests to
    the Google Bard API.
 2. ChatApplication.java: Implements the chat application GUI and
    handles user interactions.
 3. README.md: Documentation file explaining the project and how to use
    it.

## Discord Chatbot Extension (In Progress)

An extension of the Chat Application is being developed to create a Discord chatbot. The Discord chatbot will provide the same functionalities as the Chat Application but will be accessible within a Discord server.

Specifications for the Discord chatbot extension:

-   **Platform**: Discord
-   **Programming Language**: Java (using Discord Java Api)
-   **Features**: Real-time chat interaction, command-based communication, integration with the Google Bard API for responses.

The Discord chatbot extension is currently in development and is avaialaible in the  following link [https://github.com/kiaririka/HealthDiscordBotMaven] will be deployed soon.
