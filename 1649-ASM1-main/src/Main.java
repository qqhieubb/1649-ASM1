import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import implementations.Queue;
import implementations.Stack;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> messageQueue = new Queue<>();
        Stack<String> messageStack = new Stack<>();

        Scanner scanner = new Scanner(System.in);

        Integer choice = 0;
        while (choice != 6) {
            System.out.println("╔══════════════════════════╗");
            System.out.println("║           MENU           ║");
            System.out.println("╟──────────────────────────╢");
            System.out.println("║        Person A          ║");
            System.out.println("║   1. Compose messages    ║");
            System.out.println("║   2. Sent messages       ║");
            System.out.println("║   3. Delete messages     ║");
            System.out.println("╟──────────────────────────╢");
            System.out.println("║        Person B          ║");
            System.out.println("║   5. Read message        ║");
            System.out.println("╟──────────────────────────╢");
            System.out.println("║   6. Exit                ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            try {



                switch (choice) {
                    case 1:
                        System.out.print("Enter the number of messages to compose: ");
                        int numMessages = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        if (numMessages <= 0) {
                            System.out.println("Invalid number of messages. Please enter a positive integer.");
                            break;
                        }

                        long startTime = System.nanoTime();
                        for (int i = 0; i < numMessages; i++) {
                            System.out.print("Compose message " + (i + 1) + ": ");
                            String message = scanner.nextLine();
                            messageQueue.enqueue(message);
                        }
                        long endTime = System.nanoTime(); // Lưu thời điểm kết thúc thực hiện phương thức
                        long elapsedTime = endTime - startTime; // Tính thời gian thực hiện trong nanoseconds

                        double milliseconds = (double) elapsedTime / 1000000; // Chuyển đổi sang milliseconds

                        System.out.println("Messages composed and saved to Queue in " + milliseconds + " milliseconds.");

                        break;


                    case 2:
                        if (messageQueue.isEmpty()) {
                            System.out.println("No draft messages to send.");
                            break;
                        }
                        long startTime2 = System.nanoTime();
                        while (!messageQueue.isEmpty()) {
                            String sentMessage = messageQueue.dequeue();
                            messageStack.push(sentMessage);

                            System.out.println("Message sent: " + sentMessage);

//                            Thread.sleep(1000); // Add a delay of 1 second
                        }
                        long endTime2 = System.nanoTime(); // Lưu thời điểm kết thúc thực hiện phương thức
                        long elapsedTime2 = endTime2 - startTime2; // Tính thời gian thực hiện trong nanoseconds

                        double milliseconds2 = (double) elapsedTime2 / 1000000; // Chuyển đổi sang milliseconds

                        System.out.println("Messages have sent in " + milliseconds2 + " milliseconds.");
                        break;


                    case 3:
                        if (messageQueue.isEmpty()) {
                            System.out.println("No draft messages to delete.");
                            break;
                        }
                        long startTime3 = System.nanoTime();
                        System.out.println("Draft messages:");
                        int index = 1;
                        for (String draftMessage : messageQueue) {
                            System.out.println(index + ": " + draftMessage);
                            index++;
                        }
                        System.out.print("Enter the index of the message to delete: ");
                        try {
                            int deleteIndex = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            if (deleteIndex < 1 || deleteIndex > messageQueue.size()) {
                                System.out.println("Invalid index.");
                                break;
                            }

                            messageQueue.removeElementAt(deleteIndex - 1);
                            System.out.println("Message deleted.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid index.");
                        }

                        long endTime3 = System.nanoTime(); // Lưu thời điểm kết thúc thực hiện phương thức
                        long elapsedTime3 = endTime3 - startTime3; // Tính thời gian thực hiện trong nanoseconds

                        double milliseconds3 = (double) elapsedTime3 / 1000000; // Chuyển đổi sang milliseconds
                        System.out.println("Method execution time: " + milliseconds3 + " milliseconds");
                        break;


                    case 5:
                        if (messageStack.isEmpty()) {
                            System.out.println("No messages from Person A.");
                            break;
                        }

                        System.out.println("Person A's messages:");
                        index = 1;
                        for (String unreadMessage : messageStack) {
                            System.out.println(index + ": " + unreadMessage);
                            index++;
                        }
                        break;

                    case 6:
                        System.out.println("Exiting the application.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }
}
