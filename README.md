# SwingsGuiChatbot


Welcome to the Chat Application! This is a simple chatbot application built using Java Swing. It allows users to interact with a chatbot, send messages, and receive replies.


**Features**
User-friendly graphical user interface (GUI) built with Java Swing.
Chat area to display messages between the user and the chatbot.
Input field for users to enter messages and send them to the chatbot.
Messages are displayed in a conversation-like format, with a sender label for each message.
The chatbot responds to specific commands such as /about, /help, and /talk.
Automatic scrolling to the bottom of the chat window when new messages are rendered.


**Getting Started**
  To run the Chat Application locally on your machine, follow these steps:
  
  git clone https://github.com/your-username/chat-application.git

  Open the project in your preferred Java IDE.

  Build the project and resolve any dependencies.

  Run the ChatApplication class.

  The Chat Application GUI will open, and you can start interacting with the chatbot.
  
**Usage**

Enter your message in the input field at the bottom of the application.
Press the "Send" button or press Enter to send the message.
The chatbot will process your message and provide a response.
You can use the following commands:
/about: Get information about the chatbot.
/help: Display a list of available commands.


**Customization**

Feel free to customize and enhance the Chat Application to fit your needs. Here are a few ideas:

Implement additional chatbot commands and functionalities.
Improve the user interface by adding icons, colors, or themes.
Connect the chatbot to external APIs or databases for more advanced interactions.
Implement data persistence to save chat histories or user preferences.


**API Integration**
The chatbot uses a custom Google Bard API to generate responses. The API integration is handled in the PostRequest class.


**Project Structure**

The project consists of the following files:

PostRequest.java: Contains the code for making HTTP POST requests to the Google Bard API.
ChatApplication.java: Implements the chat application GUI and handles user interactions.
README.md: Documentation file explaining the project and how to use it.
