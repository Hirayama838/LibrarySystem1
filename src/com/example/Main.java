package com.example;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BookRepository bookRepository = new BookManager();
		MemberManager memberManager = new MemberManager();

		LibraryService service = new LibraryService(bookRepository, memberManager);

		// データ作成
		Book book1 = new Book("001", "Java入門", "山田");
		Book book2 = new Book("002", "Spring入門", "佐藤");

		bookRepository.save(book1);
		bookRepository.save(book2);

		Member member = new Member("m001", "田中", "tanaka@example.com");
		memberManager.addMember(member);

		// 貸出
		service.borrowBook("m001", "001");

		// 確認
		System.out.println(book1.isAvailable()); // false

		// 返却
		service.returnBook("m001", "001");

		System.out.println(book1.isAvailable()); // true
	}

}
