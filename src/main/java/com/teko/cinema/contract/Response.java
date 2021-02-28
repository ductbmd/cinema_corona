package com.teko.cinema.contract;

public enum Response {
	SUCCESS(200),
	FAIL(205),
	SERVER_ERROR(500),
	DB_ERROR(501),
	LOGIC_ERROR(503);
	private int code;
	 
    private Response(int code) {
        this.code = code;
    }
    public int getCode() {
    	return code;
    }
}
