import java.util.*;

public class Library {
    private Map<String, LibraryItem> catalog = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();
    private Set<String> borrowedIds = new HashSet<>();

    public void addItem(LibraryItem item) {
        catalog.put(item.getId(), item);
        System.out.println("Added item successfully with ID: " + item.getId());
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
        System.out.println("Added member successfully with ID: " + member.getMemberId());
    }

    public void borrowItem(String memberId, String itemId) throws LibraryException {
        if (!members.containsKey(memberId)) {
            throw new LibraryException("Member ID " + memberId + " does not exist.");
        }
        if (!catalog.containsKey(itemId)) {
            throw new LibraryException("Item ID " + itemId + " does not exist.");
        }

        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (item.isBorrowed()) {
            throw new LibraryException("Could not borrow: item " + itemId + " is already out.");
        }

        if (!member.canBorrowMore()) {
            throw new LibraryException("Member " + memberId + " has reached their borrowing limit.");
        }

        item.markBorrowed();
        member.addBorrowedItem(item);
        borrowedIds.add(itemId);
        System.out.println("Borrowed " + itemId + " to " + memberId + ".");
    }

    public void returnItem(String memberId, String itemId) throws LibraryException {
        if (!members.containsKey(memberId)) {
            throw new LibraryException("Member ID " + memberId + " does not exist.");
        }
        if (!catalog.containsKey(itemId)) {
            throw new LibraryException("Item ID " + itemId + " does not exist.");
        }

        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (!member.getBorrowedItems().contains(item)) {
            throw new LibraryException("Member " + memberId + " does not hold item " + itemId + ".");
        }

        item.markReturned();
        member.removeBorrowedItem(item);
        borrowedIds.remove(itemId);
        System.out.println("Returned " + itemId + " successfully.");
    }

    public void listCatalog() {
        if (catalog.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }
        for (LibraryItem item : catalog.values()) {
            item.displayInfo(); // Polymorphism in action!
        }
    }

    public void printReport() {
        Map<String, Integer> typeCounts = new HashMap<>();
        typeCounts.put("Book", 0);
        typeCounts.put("Magazine", 0);
        typeCounts.put("DVD", 0);

        for (LibraryItem item : catalog.values()) {
            String type = item.getType();
            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
        }

        System.out.println("---------- REPORT ----------");
        System.out.println("Total items    : " + catalog.size());
        System.out.println("Currently out  : " + borrowedIds.size());
        System.out.println("Borrowed ids   : " + borrowedIds);
        System.out.println("Items by type  : " + typeCounts);
        System.out.println("Total created  : " + LibraryItem.getTotalItemsCreated());
        System.out.println("----------------------------");
    }
}