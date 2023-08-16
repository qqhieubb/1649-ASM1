import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
public class MessagingAppB {
    private Stack<Message> messageStack = new Stack<>();

    public void receiveMessages(Queue<Message> messages) {
        messageStack.addAll(messages);
    }

    public void viewUnreadMessages() {
        int messageIndex = 1;
        for (Message message : messageStack) {
            if (!message.isRead()) {
                System.out.println("[" + messageIndex + "] " + message.toString());
            }
            messageIndex++;
        }
    }

    public void markMessageAsRead(int messageIndex) {
        if (messageIndex >= 1 && messageIndex <= messageStack.size()) {
            Message message = messageStack.get(messageStack.size() - messageIndex);
            message.markAsRead();
            System.out.println("Message marked as read: " + message.getContent());
        } else {
            System.out.println("Invalid message index.");
        }
    }

    public boolean hasUnreadMessages() {
        return messageStack.stream().anyMatch(message -> !message.isRead());
    }
}
