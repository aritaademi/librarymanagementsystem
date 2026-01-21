package com.example.demo.pojo.dto;

import jakarta.validation.constraints.NotNull;

public class BorrowBookRequest {

    @NotNull(message = "Book ID is required")
    private Integer bookId;

    @NotNull(message = "Member ID is required")
    private Integer memberId;

    public BorrowBookRequest() {}

    public BorrowBookRequest(Integer bookId, Integer memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getMemberId() {
        return memberId;
    }
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
