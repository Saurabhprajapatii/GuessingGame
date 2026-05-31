import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ArrayList to store tasks
        ArrayList<String> tasks = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {

                // Add Task
                case 1:
                    System.out.print("Enter task: ");
                    String task = sc.nextLine();

                    tasks.add(task);

                    System.out.println("Task added successfully!");
                    break;

                // View Tasks
                case 2:

                    if (tasks.isEmpty()) {
                        System.out.println("No tasks available.");
                    } else {
                        System.out.println("\nTask List:");

                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }

                    break;

                // Update Task
                case 3:

                    if (tasks.isEmpty()) {
                        System.out.println("No tasks available.");
                    } else {

                        System.out.println("\nTasks List:");

                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }

                        System.out.print("Enter task number to update: ");
                        int updateIndex = sc.nextInt();
                        sc.nextLine();

                        if (updateIndex > 0 && updateIndex <= tasks.size()) {

                            System.out.print("Enter new task: ");
                            String newTask = sc.nextLine();

                            tasks.set(updateIndex - 1, newTask);

                            System.out.println("Task updated successfully!");

                        } else {
                            System.out.println("Invalid task number.");
                        }
                    }

                    break;

                // Delete Task
                case 4:

                    if (tasks.isEmpty()) {
                        System.out.println("No tasks available.");
                    } else {

                        System.out.println("\nTasks List:");

                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }

                        System.out.print("Enter task number to delete: ");
                        int deleteIndex = sc.nextInt();

                        if (deleteIndex > 0 && deleteIndex <= tasks.size()) {

                            tasks.remove(deleteIndex - 1);

                            System.out.println("Task deleted successfully!");

                        } else {
                            System.out.println("Invalid task number.");
                        }
                    }

                    break;

                // Exit
                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}