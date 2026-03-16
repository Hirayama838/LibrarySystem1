package com.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 図書館の蔵書全体を管理するクラス 書籍の追加、検索、在庫確認などの機能を提供する
 */

public class BookManager {
	// ISBNをキーとした書籍のマップ
	private Map<String, Book> books;

	/**
	 * BookManagerの初期化を行うコンストラクタ
	 */
	public BookManager() {
		this.books = new HashMap<>();
	}

	/**
	 * 新しい書籍を蔵書に追加する
	 * 
	 * @param book 追加する書籍オブジェクト
	 * @throws IllegalArgumentException 既に同じISBNの書籍が存在する場合
	 */

	public void addBook(Book book) {
		if (books.containsKey(book.getIsbn())) {
			throw new IllegalArgumentException("この本は既に登録されています:" + book.getIsbn());
		}
		books.put(book.getIsbn(), book);
	}

	/**
	 * ISBNから書籍を検索する
	 * 
	 * @param isbn 検索するISBN
	 * @return 該当する書籍。存在しない場合はnull
	 */

	public Book findByIsbn(String isbn) {
		return books.get(isbn);
	}

	/**
	 * キーワードで書籍を検索する タイトルまたは著者名に指定したキーワードを含む書籍を返す
	 * 
	 * @param keyword 検索キーワード
	 * @return 検索結果の書籍リスト
	 */
	public List<Book> searchBooks(String keyword) {
		return books.values().stream()
				.filter(book -> book.getTitle().contains(keyword) || book.getAuthor().contains(keyword))
				.collect(Collectors.toList());
	}

	/**
	 * 現在貸出可能な書籍のリストを取得する
	 * 
	 * @return 貸出可能な書籍のリスト
	 */

	public List<Book> getAvailableBooks() {
		return books.values().stream().filter(Book::isAvailable).collect(Collectors.toList());
	}

	/**
	 * 登録されている全書籍の数を返す
	 * 
	 * @return 蔵書数
	 */
	public int getTotalBookCount() {
		return books.size();
	}

	public List<Book> findOverdueBooks() {

		LocalDate today = LocalDate.now();

		return books.values().stream().filter(book -> !book.isAvailable()).filter(book -> book.getDueDate() != null)
				.filter(book -> book.getDueDate().isBefore(today)).collect(Collectors.toList());
	}
	
	public void removeBook(String isbn) {
		
		Book book = books.get(isbn);
		
		if (book == null) {
			throw new IllegalArgumentException("本が見つかりません");
		}
		
		if (!book.isAvailable()) {
			throw new IllegalStateException("貸出中の本は削除できません");
		}
		
		books.remove(isbn);
	}

}
