package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 図書館の会員を管理するクラス 会員情報と貸出中の書籍リストを管理する
 */

public class Member {
	// 会員を一意に識別するID
	private String memberId;
	// 会員の名前
	private String name;
	// 会員のメールアドレス
	private String email;
	// 貸出中の書籍のISBNリスト
	private List<String> borrowedBooks;

	public Member(String memberId, String name, String email) {
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.borrowedBooks = new ArrayList<>(); // 空の貸出リストで初期化
	}

	// Getterメソッド
	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getBorrowedBooks() {
		return new ArrayList<>(borrowedBooks);
	}

	public void borrowBook(String isbn) {
		borrowedBooks.add(isbn);
	}

	public void returnBook(String isbn) {
		borrowedBooks.remove(isbn);
	}

	public int getBorrowedCount() {
		return borrowedBooks.size();
	}

}
