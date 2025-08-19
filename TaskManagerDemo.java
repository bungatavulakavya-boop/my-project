import java.util.Scanner; 
 
// Abstract base class 
abstract class AbstractTaskList { 
    abstract void addTask(String name, int priority); 
    abstract void showTasks(); 
    abstract boolean deleteTask(String name); 
 
    // Static nested class so subclasses can use it directly 
    static class TaskNode { 
        String taskName; 
        int priority; 
        TaskNode next; 
        TaskNode prev; 
 
        TaskNode(String name, int priority) { 
            this.taskName = name; 
            this.priority = priority; 
            this.next = null; 
            this.prev = null; 
        } 
    } 
} 
 
// Doubly linked list implementation of task list 
class DoublyTaskList extends AbstractTaskList { 
    private TaskNode head; 
 
    @Override 
    void addTask(String name, int priority) { 
        TaskNode newNode = new TaskNode(name, priority); 
        if (head == null) { 
            head = newNode; 
        } else { 
            TaskNode temp = head; 
            while (temp.next != null) { 
                temp = temp.next; 
            } 
            temp.next = newNode; 
            newNode.prev = temp; 
        } 
        System.out.println("Task added successfully."); 
    } 
 
    @Override 
    void showTasks() { 
        if (head == null) { 
            System.out.println("No tasks to show."); 
            return; 
        } 
        TaskNode temp = head; 
        System.out.print("Tasks List: "); 
        while (temp != null) { 
            System.out.print("[Task: \"" + temp.taskName + "\", Priority: " + temp.priority + "] <-> "); 
            temp = temp.next; 
        } 
        System.out.println("null"); 
    } 
 
    @Override 
    boolean deleteTask(String name) { 
        TaskNode temp = head; 
        while (temp != null) { 
            if (temp.taskName.equalsIgnoreCase(name)) { 
                if (temp.prev != null) { 
                    temp.prev.next = temp.next; 
                } else { 
                    head = temp.next; // deleting head node 
                } 
                if (temp.next != null) { 
                    temp.next.prev = temp.prev; 
                } 
                System.out.println("Task \"" + name + "\" deleted successfully."); 
                return true; 
            } 
            temp = temp.next; 
        } 
        System.out.println("Task \"" + name + "\" not found."); 
        return false; 
    } 
} 
 
// Main program with menu 
public class TaskManagerDemo { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        AbstractTaskList taskList = new DoublyTaskList(); 
        int choice; 
 
        do { 
            System.out.println("\n==== Menu ===="); 
            System.out.println("1. Add Item"); 
            System.out.println("2. View Items"); 
            System.out.println("3. Delete Item"); 
            System.out.println("4. Exit"); 
            System.out.print("Enter your choice: "); 
 
            while (!sc.hasNextInt()) { 
                System.out.println("Invalid input! Please enter a number."); 
                sc.next(); // discard invalid input 
            } 
            choice = sc.nextInt(); 
            sc.nextLine(); // consume newline 
 
            switch (choice) { 
                case 1: 
                    System.out.print("Enter task name: "); 
                    String name = sc.nextLine(); 
                    System.out.print("Enter priority (integer): "); 
                    while (!sc.hasNextInt()) { 
                        System.out.println("Invalid input! Enter a valid integer priority."); 
                        sc.next(); 
                    } 
                    int priority = sc.nextInt(); 
                    sc.nextLine(); // consume newline 
                    taskList.addTask(name, priority); 
                    break; 
 
                case 2: 
                    taskList.showTasks(); 
                    break; 
 
                case 3: 
                    System.out.print("Enter task name to delete: "); 
                    String delName = sc.nextLine(); 
                    taskList.deleteTask(delName); 
                    break; 
 
                case 4: 
                    System.out.println("Exiting..."); 
                    break; 
 
                default: 
                    System.out.println("Invalid choice! Please try again."); 
            } 
        } while (choice != 4); 
 
        sc.close(); 
    } 
} 
 