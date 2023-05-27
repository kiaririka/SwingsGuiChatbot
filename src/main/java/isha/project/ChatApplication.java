package isha.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatApplication {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JPanel messagePanel;

    public ChatApplication() {
        frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

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
        displayMessage("Bot", "Welcome to the chat! How can I assist you?", false);

        frame.setVisible(true);
    }

    private void processMessage(String messageContent) {
        PostRequest request = new PostRequest();
        String reply;
        if (messageContent.equalsIgnoreCase("/about")) {
            reply = "I am a chatbot made with the intent of helping anyone and everyone looking for a friend to talk to.\nFeel free to ask me anything or simply chat with me.";
        } else if (messageContent.equalsIgnoreCase("/help")) {
            reply = "Here are some options you can ask me:\n1. /about - To know more about me\n2. /help - To know more options to ask me\n3. /talk - To have a one-on-one talk session";
        } else if (messageContent.equalsIgnoreCase("/talk")) {
            reply = "Sure! Let's have a one-on-one talk.";
        } else {
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
