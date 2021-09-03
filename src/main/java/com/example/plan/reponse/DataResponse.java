package com.example.plan.reponse;

import lombok.Data;

@Data
public class DataResponse {
	private Object data;
	private String msg = "操作成功";

	public DataResponse (Object data) {
		this.data = data;
	}

	public DataResponse (Object data, String msg) {
		this.data = data;
		this.msg = msg;
	}

}
