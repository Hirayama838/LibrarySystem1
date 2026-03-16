package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 図書館の会員を管理するクラス 会員の追加、検索、削除などの機能を提供する
 */
public class MemberManager {
	// 会員IDをキーとした会員のマップ
	private Map<String, Member> members;

	/**
	 * MemberManagerの初期化を行うコンストラクタ
	 */
	public MemberManager() {
		this.members = new HashMap<>();
	}

	/**
	 * 新しい会員を追加する
	 * 
	 * @param member 追加する会員オブジェクト
	 * @throws IllegalArgumentException 既に同じIDの会員が存在する場合
	 */
	public void addMember(Member member) {
		if (members.containsKey(member.getMemberId())) {
			throw new IllegalArgumentException("この会員IDは既に使用されています: " + member.getMemberId());
		}
		members.put(member.getMemberId(), member);
	}

	/**
	 * 会員IDから会員を検索する
	 * 
	 * @param memberId 検索する会員ID
	 * @return 該当する会員。存在しない場合はnull
	 */
	public Optional<Member> findById(String memberId) {
		return Optional.ofNullable(members.get(memberId));
	}

	/**
	 * 会員を削除する
	 * 
	 * @param memberId 削除する会員のID
	 * @throws IllegalStateException 貸出中の本がある場合
	 */


	/**
	 * 登録されている全会員数を返す
	 * 
	 * @return 会員数
	 */
	public int getTotalMemberCount() {
		return members.size();
	}
}
