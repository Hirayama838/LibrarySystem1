package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 図書館の蔵書全体を管理するクラス 書籍の追加、検索、在庫確認などの機能を提供する
 */

public class BookManager implements BookRepository {
	// ISBNをキーとした書籍のマップ
	private Map<String, Book> books;

	/**
	 * BookManagerの初期化を行うコンストラクタ
	 */
	public BookManager() {
		this.books = new HashMap<>();
	}

	@Override
	public void save(Book book) {
		if (books.containsKey(book.getIsbn())) {
			throw new IllegalArgumentException("この本は既に登録されています:" + book.getIsbn());
		}
		books.put(book.getIsbn(), book);
	}

	@Override
	public Optional<Book> findByIsbn(String isbn) {
		return Optional.ofNullable(books.get(isbn));
	}

	@Override
	public List<Book> findAll() {
		return new ArrayList<>(books.values());
	}

	public List<Book> searchBooks(String keyword) {
		return books.values().stream()
				.filter(book -> book.getTitle().contains(keyword) || book.getAuthor().contains(keyword))
				.collect(Collectors.toList());
	}

	public List<Book> getAvailableBooks() {
		return books.values().stream().filter(Book::isAvailable).collect(Collectors.toList());
	}

	public int getTotalBookCount() {
		return books.size();
	}

	public List<Book> findOverdueBooks() {

		LocalDate today = LocalDate.now();

		return books.values().stream().filter(book -> !book.isAvailable()).filter(book -> book.getDueDate() != null)
				.filter(book -> book.getDueDate().isBefore(today)).collect(Collectors.toList());
	}

	@Override
	public void remove(String isbn) {
		Book book = findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("本が見つかりません"));

		if (!book.isAvailable()) {
			throw new IllegalStateException("貸出中の本は削除できません");
		}

		books.remove(isbn);

	}

	@Override
	public List<Book> search(String keyword) {
		return books.values().stream()
				.filter(book -> book.getTitle().contains(keyword) || book.getAuthor().contains(keyword))
				.collect(Collectors.toList());
	}

}
