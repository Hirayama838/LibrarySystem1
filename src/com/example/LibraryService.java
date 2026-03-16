package com.example;

import java.util.List;

/**
 * 図書館の主要な業務ロジックを提供するサービスクラス 書籍の貸出/返却、検索などの機能を統合的に管理する
 */
public class LibraryService {
	// 書籍管理用のマネージャー
	private final BookManager bookManager;
	// 会員管理用のマネージャー
	private final MemberManager memberManager;
	// 標準の貸出期間（日数）
	private static final int DEFAULT_BORROW_DAYS = 14;

    public LibraryService(BookManager bookManager,
            MemberManager memberManager) {
    	this.bookManager = bookManager;
    	this.memberManager = memberManager;
	}

	/**
	 * 書籍の貸出処理を行う
	 * 
	 * @param memberId 借りる会員のID
	 * @param isbn     借りる書籍のISBN
	 * @throws IllegalArgumentException 会員または書籍が存在しない場合
	 * @throws IllegalStateException    書籍が貸出中の場合
	 */
	public void borrowBook(String memberId, String isbn) {
		// 会員と書籍の存在確認
		Member member = memberManager.findById(memberId).orElseThrow(() -> new IllegalArgumentException("会員が見つかりません"));
		Book book = bookManager.findByIsbn(isbn);

		if (member == null || book == null) {
			throw new IllegalArgumentException("会員または書籍が見つかりません");
		}

		// 貸出可能かの確認
		if (!book.isAvailable()) {
			throw new IllegalStateException("この本は現在貸出できません");
		}

		// 貸出処理の実行
		book.borrowBook(memberId, DEFAULT_BORROW_DAYS);
		member.borrowBook(isbn);
	}

	/**
	 * 書籍の返却処理を行う
	 * 
	 * @param memberId 返却する会員のID
	 * @param isbn     返却する書籍のISBN
	 * @throws IllegalArgumentException 会員または書籍が存在しない場合
	 * @throws IllegalStateException    別の会員が借りている場合
	 */
	public void returnBook(String memberId, String isbn) {
		// 会員と書籍の存在確認
		Member member = memberManager.findById(memberId).orElseThrow(() -> new IllegalArgumentException("会員が見つかりません"));
		Book book = bookManager.findByIsbn(isbn);

		if (member == null || book == null) {
			throw new IllegalArgumentException("会員または書籍が見つかりません");
		}

		// 返却権限の確認
		if (!memberId.equals(book.getBorrowedBy())) {
			throw new IllegalStateException("この本は別の会員が借りています");
		}

		// 返却処理の実行
		book.returnBook();
		member.returnBook(isbn);
	}

	/**
	 * キーワードによる書籍検索を行う
	 * 
	 * @param keyword 検索キーワード
	 * @return 検索結果の書籍リスト
	 */
	public List<Book> searchBooks(String keyword) {
		return bookManager.searchBooks(keyword);
	}

	// Getterメソッド
	public BookManager getBookManager() {
		return bookManager;
	}
}
