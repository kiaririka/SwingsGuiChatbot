package isha.project;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChatApplication {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JPanel messagePanel;
    private JsonArray messages;

    public ChatApplication() {
        frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Message input field
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageContent = inputField.getText();
                processMessage(messageContent);
                inputField.setText("");
            }
        });

        // Send button
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageContent = inputField.getText();
                processMessage(messageContent);
                inputField.setText("");
            }
        });

        // Message input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Message panel for user and bot messages
        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        JScrollPane messageScrollPane = new JScrollPane(messagePanel);
        frame.add(messageScrollPane, BorderLayout.CENTER);

        // Welcome message
        displayMessage("Bot", "Welcome to the chat! I am a chatbot made with the intent of helping anyone and everyone looking for a friend to talk to. \nNow tell me how I can assist you today? \n Write \"what can i ask you?\" to get some samples question to ask me.", false);

        frame.setVisible(true);

        // Load messages from JSON file
        loadMessages();
    }

    private void loadMessages() {
        InputStream inputStream = getClass().getResourceAsStream("/messages.json");
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        messages = JsonParser.parseReader(reader).getAsJsonArray();
    }

    private void processMessage(String messageContent) {
        boolean isBotMessage = false;
        String reply = "";

        if (messageContent.equalsIgnoreCase("what can I ask you?")) {
            // Provide probable questions from the messages
            StringBuilder questionList = new StringBuilder("Here are some probable questions you can ask me:\n");

            for (int i = 0; i < messages.size(); i++) {
                JsonObject messageObject = messages.get(i).getAsJsonObject();
                String message = messageObject.get("message").getAsString();
                questionList.append("- ").append(message).append("\n");
            }

            reply = questionList.toString();
            isBotMessage = true;
        } else {
            for (int i = 0; i < messages.size(); i++) {
                JsonObject messageObject = messages.get(i).getAsJsonObject();
                String message = messageObject.get("message").getAsString();
                String messageReply = messageObject.get("reply").getAsString();

                if (messageContent.equalsIgnoreCase(message)) {
                    reply = messageReply;
                    isBotMessage = true;
                    break;
                }
            }
        }

        if (!isBotMessage) {
            // Fetch reply from PostRequest class
            PostRequest request = new PostRequest();
            try {
                reply = request.post(messageContent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        displayMessage("You", messageContent, true);
        displayMessage("Bot", reply, false);
    }

    private void displayMessage(String sender, String message, boolean isUserMessage) {
        JTextArea messageTextArea = new JTextArea(message);
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setBackground(isUserMessage ? Color.decode("#DCF8C6") : Color.decode("#E2E2E2"));
        messageTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        messageTextArea.setMargin(new Insets(5, 5, 5, 5));
        messageTextArea.setFont(messageTextArea.getFont().deriveFont(Font.PLAIN));
        messageTextArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, messageTextArea.getPreferredSize().height));

        JLabel senderLabel = new JLabel(sender);
        senderLabel.setFont(senderLabel.getFont().deriveFont(Font.BOLD));
        JPanel senderPanel = new JPanel(new FlowLayout(isUserMessage ? FlowLayout.LEFT : FlowLayout.RIGHT));
        senderPanel.add(senderLabel);

        JPanel messageWrapper = new JPanel(new BorderLayout());
        messageWrapper.setBackground(isUserMessage ? Color.decode("#DCF8C6") : Color.decode("#E2E2E2"));
        messageWrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10),
                BorderFactory.createLineBorder(Color.decode("#BFBFBF"), 1)));
        messageWrapper.add(senderPanel, BorderLayout.NORTH);
        messageWrapper.add(messageTextArea, BorderLayout.CENTER);

        messagePanel.add(messageWrapper);
        messagePanel.revalidate();
        messagePanel.repaint();

        // Scroll to the bottom of the chat area
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatApplication();
            }
        });
    }
}
