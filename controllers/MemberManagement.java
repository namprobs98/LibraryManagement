package Java.LibraryManagement.controllers;

import Java.LibraryManagement.models.Member;
import java.util.Map;
import java.util.Scanner;

public class MemberManagement {
    Scanner sc = new Scanner(System.in);
    private Map<String, Member> members;

    public MemberManagement(Map<String, Member> members) {
        this.members = members;
    }

    public void manageMembers() {
        // Code to manage member operations
        System.out.println();
        System.out.println("Member Management System");
        System.out.println("1. Add Member");
        System.out.println("2. View Member List");
        System.out.println("3. Update Member");
        System.out.println("4. Delete Member");
        System.out.println("5. Exit");
        int choice = 0;
        while (choice != 5) {
            System.out.print("Enter your choice: ");
            String choiceStr = sc.nextLine();
            while (!isValidInt(choiceStr, 1, 5)) {
                System.out.print("Invalid input. Please enter a number between 1 and 5: ");
                choiceStr = sc.nextLine();
            }
            choice = Integer.parseInt(choiceStr);
            switch (choice) {
                case 1:
                    // Code to add a member
                    addMember();
                    break;
                case 2:
                    // Code to view members
                    viewMembers();
                    break;
                case 3:
                    // Code to update member information
                    updateMember();
                    break;
                case 4:
                    // Code to delete a member
                    deleteMember();
                    break;
                case 5:
                    System.out.println("Exiting Member Management. Returning to main menu.");
                    break;
            }
        }

    }

    //add sample members
    public void addMember() {
        // Code to add a member
        System.out.println("Adding a new member...");
        System.out.println("Enter member_id: ");
        String memberId = sc.nextLine();
        // Check if member_id already exists
        if (members.containsKey(memberId)) {
            System.out.println("Member ID already exists. Please try again.");
            return;
        };
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        Member newMember = new Member(memberId, name, email, phoneNumber);
        members.put(memberId, newMember);
        System.out.println("Member added successfully.");   
    }   
    //view member list
    public void viewMembers() {
        // Code to view members
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            System.out.println("Member List:");
            for (Member member : members.values()) {
                System.out.println("ID: " + member.getMember_id() + ", Name: " + member.getMember_name());
            }
        }
    }

    //update member information
    public void updateMember() {
        // Code to update member information
        System.out.println("Enter member ID to update: ");
        String memberId = sc.nextLine();
        if (!members.containsKey(memberId)) {
            System.out.println("Member ID not found. Please try again.");
            return;
        }
        Member memberToUpdate = members.get(memberId);
        System.out.println("Enter new name (leave blank to keep current): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            memberToUpdate.setMember_name(newName);
        }
        System.out.println("Enter new email (leave blank to keep current): ");
        String newEmail = sc.nextLine();
        if (!newEmail.isEmpty()) {
            memberToUpdate.setMember_email(newEmail);
        }
        System.out.println("Enter new phone number (leave blank to keep current): ");
        String newPhoneNumber = sc.nextLine();
        if (!newPhoneNumber.isEmpty()) {
            memberToUpdate.setMember_phone(newPhoneNumber);
        }
        System.out.println("Member information updated successfully.");
    }
    
    //delete a member
    public void deleteMember() {
        // Code to delete a member
        System.out.println("Enter member ID to delete: ");
        String memberId = sc.nextLine();
        if (!members.containsKey(memberId)) {
            System.out.println("Member ID not found. Please try again.");
            return;
        }
        members.remove(memberId);
        System.out.println("Member deleted successfully.");
    }

    //validate integer input
    private boolean isValidInt(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
