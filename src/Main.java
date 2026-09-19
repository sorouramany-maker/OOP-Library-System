import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        try {
            library.addItem(new Book("Clean Code", "Robert Martin", 464));
            library.addItem(new Magazine("National Geographic", 204));
            library.addItem(new DVD("Inception", 148));
            library.addMember(new Member("M1", "Alice", 3));
            library.addMember(new Member("M2", "Bob", 2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (running) {
            System.out.println("\n===== Library Lending System =====");
            System.out.println("1. Add Item");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. List Catalog");
            System.out.println("6. Report");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter type (Book / Magazine / DVD): ");
                        String type = scanner.nextLine().trim();
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();

                        if (type.equalsIgnoreCase("Book")) {
                            System.out.print("Enter author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter pages: ");
                            int pages = Integer.parseInt(scanner.nextLine());
                            library.addItem(new Book(title, author, pages));
                        } else if (type.equalsIgnoreCase("Magazine")) {
                            System.out.print("Enter issue number: ");
                            int issueNum = Integer.parseInt(scanner.nextLine());
                            library.addItem(new Magazine(title, issueNum));
                        } else if (type.equalsIgnoreCase("DVD")) {
                            System.out.print("Enter runtime in minutes: ");
                            int runtime = Integer.parseInt(scanner.nextLine());
                            library.addItem(new DVD(title, runtime));
                        } else {
                            System.out.println("Invalid item type.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter member ID: ");
                        String mId = scanner.nextLine();
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter max allowed items: ");
                        int max = Integer.parseInt(scanner.nextLine());
                        library.addMember(new Member(mId, name, max));
                        break;

                    case 3:
                        System.out.print("Member id: ");
                        String borrowerId = scanner.nextLine();
                        System.out.print("Item id: ");
                        String bItemId = scanner.nextLine();
                        library.borrowItem(borrowerId, bItemId);
                        break;

                    case 4:
                        System.out.print("Member id: ");
                        String returnerId = scanner.nextLine();
                        System.out.print("Item id: ");
                        String rItemId = scanner.nextLine();
                        library.returnItem(returnerId, rItemId);
                        break;

                    case 5:
                        library.listCatalog();
                        break;

                    case 6:
                        library.printReport();
                        break;

                    case 7:
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice, please enter a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            } catch (LibraryException e) {
                System.out.println("Could not process: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Validation Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}