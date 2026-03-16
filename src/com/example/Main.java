package com.example;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        BookManager bookManager = new BookManager();
        MemberManager memberManager = new MemberManager();

        LibraryService libraryService =
                new LibraryService(bookManager, memberManager);

	}

}
