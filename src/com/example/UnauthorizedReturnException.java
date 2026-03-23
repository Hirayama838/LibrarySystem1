package com.example;

public class UnauthorizedReturnException extends RuntimeException {
    public UnauthorizedReturnException(String memberId, String isbn) {
        super("この会員は返却できません: memberId=" + memberId + ", isbn=" + isbn);
    }
}