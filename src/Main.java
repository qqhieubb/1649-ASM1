import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MessagingAppA messagingAppA = new MessagingAppA();
        MessagingAppB messagingAppB = new MessagingAppB();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=========Menu==========");
            System.out.println("=========A==========");
            System.out.println("1. Compose messages");
            System.out.println("2. Send messages");
            System.out.println("3. Edit messages");
            System.out.println("4. Delete messages");
            System.out.println("================================");
            System.out.println("=========B=========");
            System.out.println("5. Receive and view unread messages");
            System.out.println("6. Read message");
            System.out.println("================================");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            try {
                switch (choice) {
                    case 1:
                        System.out.print("How many messages do you want to compose? ");
                        int n = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        for (int i = 0; i < n; i++) {
                            System.out.print("Compose your message: ");
                            String messageInput = scanner.nextLine();
                            messagingAppA.composeMessage(messageInput);
                        }
                        break;
                    case 2:
                        if (!messagingAppA.hasDraftMessages()) {
                            System.out.println("No messages to send.");
                            break;
                        }
                        messagingAppA.sendMessage();
                        Queue<Message> messages = new LinkedList<>(messagingAppA.getMessageQueue());
                        messagingAppB.receiveMessages(messages);
                        System.out.println("Messages have sent.");
                        break;
                    case 3:
                        if (!messagingAppA.hasDraftMessages()) {
                            System.out.println("No messages to edit.");
                            break;
                        }
                        System.out.print("Enter the index of the message to edit: ");
                        int editIndex = scanner.nextInt();
                        if (editIndex <= 0) {
                            throw new IndexOutOfBoundsException("Invalid index.");
                        }
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Enter the new content: ");
                        String newContent = scanner.nextLine();
                        messagingAppA.editMessage(editIndex, newContent);
                        break;
                    case 4:
                        if (!messagingAppA.hasDraftMessages()) {
                            System.out.println("No messages to delete.");
                            break;
                        }
                        System.out.print("Enter the index of the message to delete: ");
                        int deleteIndex = scanner.nextInt();
                        if (deleteIndex <= 0) {
                            throw new IndexOutOfBoundsException("Invalid index.");
                        }
                        messagingAppA.deleteMessage(deleteIndex);
                        break;

                    case 5:
                        if (messagingAppB.hasUnreadMessages()) {
                            messagingAppB.viewUnreadMessages();
                        } else {
                            System.out.println("No unread messages.");
                        }
                        break;
                    case 6:
                        if (messagingAppB.hasUnreadMessages()) {
                            messagingAppB.viewUnreadMessages();
                            System.out.print("Enter the index of the message to mark as read: ");
                            int messageIndex = scanner.nextInt();
                            if (messageIndex <= 0) {
                                throw new IndexOutOfBoundsException("Invalid index.");
                            }
                            messagingAppB.markMessageAsRead(messageIndex);
                        } else {
                            System.out.println("No unread messages.");
                        }
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}