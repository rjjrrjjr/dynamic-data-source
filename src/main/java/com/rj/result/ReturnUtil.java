package com.rj.result;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReturnUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ReturnUtil.class);
	
	public static <T> Result<T> packReturn(T data, String code, String message) {
		Result<T> result = new Result<T>();
		if (ResultCode.OPERATION_SUCCESS.getCode().equals(code)) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		result.setData(data);
		result.setCode(code);
		result.setMessage(message);
		
		logger.info("Return Message: " + JSON.toJSONString(result));
		
		return result;
	}
	
	public static <T> Result<T> packReturn(T data, ResultCode resultCode) {
		Result<T> result = new Result<T>();
		if (ResultCode.OPERATION_SUCCESS.getCode().equals(resultCode.getCode())) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		result.setData(data);
		result.setCode(resultCode.getCode());
		result.setMessage(resultCode.getMessage());
		
		logger.info("Return Message: " + JSON.toJSONString(result));
		
		return result;
	}

	public static <T> Result<T> packSuccessReturn(T data) {
		Result<T> result = new Result<T>();
		result.setSuccess(true);
		result.setData(data);
		result.setCode(ResultCode.OPERATION_SUCCESS.getCode());
		result.setMessage(ResultCode.OPERATION_SUCCESS.getMessage());

		logger.info("Return Message: " + JSON.toJSONString(result));

		return result;
	}

	public static <T> Result<T> packFailedReturn(ResultCode resultCode) {
		Result<T> result = new Result<T>();
		result.setSuccess(false);
		result.setCode(resultCode.getCode());
		result.setMessage(resultCode.getMessage());

		logger.info("Return Message: " + JSON.toJSONString(result));

		return result;
	}

}
