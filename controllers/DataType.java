package Java.LibraryManagement.controllers;

import java.util.Map;

import Java.LibraryManagement.models.Book;
import Java.LibraryManagement.models.Borrow;
import Java.LibraryManagement.models.Member;




public class DataType {
    public void saveDataToText(Map<String, Book> books, Map<String, Member> members,
            java.util.List<Borrow> borrowingRecords) {
        java.nio.file.Path out = java.nio.file.Paths.get("library_data.txt");
        java.nio.file.Path tmp = java.nio.file.Paths.get("library_data.txt.tmp");
        try (java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(tmp,
                java.nio.charset.StandardCharsets.UTF_8)) {
            writer.write("Books:");
            writer.newLine();
            for (Book book : books.values()) {
                writer.write(String.join(",",
                        String.valueOf(book.getBook_id()),
                        book.getBook_title(),
                        book.getBook_author(),
                        book.getBook_genre(),
                        String.valueOf(book.getBook_copies())));
                writer.newLine();
            }
            writer.write("Members:");
            writer.newLine();
            for (Member member : members.values()) {
                writer.write(String.join(",",
                        String.valueOf(member.getMember_id()),
                        member.getMember_name(),
                        member.getMember_email(),
                        member.getMember_phone()));
                writer.newLine();
            }
            writer.write("Borrowing Records:");
            writer.newLine();
            for (Borrow borrow : borrowingRecords) {
                writer.write(String.join(",",
                        String.valueOf(borrow.getBorrow_id()),
                        String.valueOf(borrow.getMember_id()),
                        String.valueOf(borrow.getBook_id()),
                        String.valueOf(borrow.getBorrow_date()),
                        String.valueOf(borrow.getReturn_date())));
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error writing temp file: " + e.getMessage());
            return;
        }
        try {
            java.nio.file.Files.move(tmp, out, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error moving temp file into place: " + e.getMessage());
        }
    }

}
