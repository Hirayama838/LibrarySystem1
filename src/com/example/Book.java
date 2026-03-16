package com.example;

import java.time.LocalDate;

/**
 * 図書館の書籍を管理するクラス ISBN、タイトル、著者、貸出状況などの情報を保持する
 */

public class Book {
	// 書籍を一意に識別するISBNコード
	private String isbn;
	// 書籍のタイトル
	private String title;
	// 著者名
	private String author;
	// 貸出可能かどうかのフラグ
	private boolean isAvailable;
	// 現在の貸出者のID（貸出中でない場合はnull）
	private String borrowedBy;
	// 返却期限日
	private LocalDate dueDate;

	/**
	 * 書籍の新規作成を行うコンストラクタ
	 * 
	 * @param isbn   ISBN番号
	 * @param title  書籍タイトル
	 * @param author 著者名
	 */

	public Book(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.isAvailable = true; // 新規作成時は貸出可能状態
	}

	// Getterメソッド
	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public String getBorrowedBy() {
		return borrowedBy;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * 書籍の貸出処理を行う
	 * 
	 * @param memberId   貸出を行う会員のID
	 * @param borrowDays 貸出日数
	 * @throws IllegalStateException 既に貸出中の場合
	 */

	public void borrowBook(String memberId, int borrowDays) {
		if (!isAvailable) {
			throw new IllegalStateException("この本は既に貸し出されています");
		}
		this.isAvailable = false;
		this.borrowedBy = memberId;
		this.dueDate = LocalDate.now().plusDays(borrowDays);
	}

	/**
	 * 書籍の返却処理を行う
	 */

	public void returnBook() {
		this.isAvailable = true;
		this.borrowedBy = null;
		this.dueDate = null;
	}

}
