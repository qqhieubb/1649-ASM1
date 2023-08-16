import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class MessagingAppA {
    private Queue<Message> draftQueue = new LinkedList<>();
    private Queue<Message> messageQueue = new LinkedList<>();

    public void composeMessage(String content) {
        Message message = new Message(content);
        draftQueue.add(message);
    }

    public void sendMessage() throws InterruptedException {
        while (!draftQueue.isEmpty()) {
            Message message = draftQueue.poll();
            messageQueue.add(message);
            System.out.println("Sent message: " + message.getContent());
            Thread.sleep(1000); // Delay 1 second
        }
    }
    public Queue<Message> getMessageQueue() {
        return messageQueue;
    }

    public void editMessage(int index, String newContent) throws IndexOutOfBoundsException {
        if (index >= 1 && index <= draftQueue.size()) {
            Message message = (Message) draftQueue.toArray()[index - 1];
            message.setContent(newContent);
            System.out.println("Message edited.");
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    public void deleteMessage(int index) throws IndexOutOfBoundsException {
        if (index >= 1 && index <= draftQueue.size()) {
            Message removedMessage = (Message) draftQueue.toArray()[index - 1];
            draftQueue.remove(removedMessage);
            System.out.println("Message deleted.");
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    public boolean hasDraftMessages() {
        return !draftQueue.isEmpty();
    }
}
