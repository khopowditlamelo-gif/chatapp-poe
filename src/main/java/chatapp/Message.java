package chatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private static int totalSent = 0;
    private static final List<Message> sentMessages = new ArrayList<>();

    private final String messageID;
    private final int messageNum;
    private final String recipient;
    private final String text;
    private final String messageHash;

    public Message(int messageNum, String recipient, String text) {
        this(generateID(), messageNum, recipient, text);
    }

    // used by unit tests so the ID is predictable
    public Message(String messageID, int messageNum, String recipient, String text) {
        this.messageID = messageID;
        this.messageNum = messageNum;
        this.recipient = recipient;
        this.text = text;
        this.messageHash = createMessageHash();
    }

    private static String generateID() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public static boolean isValidRecipient(String r) {
        return r != null && r.matches("^\\+\\d{1,3}\\d{1,10}$");
    }

    public String checkRecipientCell() {
        if (isValidRecipient(recipient)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public static String checkMessageLength(String text) {
        if (text.length() <= 250) {
            return "Message ready to send.";
        }
        return "Message exceeds 250 characters by " + (text.length() - 250)
                + "; please reduce the size.";
    }

    public String createMessageHash() {
        String[] words = text.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        String idPart = messageID.substring(0, Math.min(2, messageID.length()));
        return (idPart + ":" + messageNum + ":" + first + last).toUpperCase();
    }

    // choice: 1 = send, 2 = disregard, 3 = store
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                totalSent++;
                sentMessages.add(this);
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append(m.details()).append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessages() {
        return totalSent;
    }

    public static void reset() {
        totalSent = 0;
        sentMessages.clear();
    }

    public String details() {
        return "Message ID: " + messageID + ", Hash: " + messageHash
                + ", Recipient: " + recipient + ", Message: " + text;
    }

    public void storeMessage() {
        try (FileWriter fw = new FileWriter("messages.json", true)) {
            fw.write(String.format(
                "{\"messageID\":\"%s\",\"messageHash\":\"%s\",\"recipient\":\"%s\",\"message\":\"%s\"}%n",
                messageID, messageHash, recipient,
                text.replace("\\", "\\\\").replace("\"", "\\\"")));
        } catch (IOException e) {
            System.out.println("Could not store message: " + e.getMessage());
        }
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageID() {
        return messageID;
    }
}