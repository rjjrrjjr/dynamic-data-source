package com.rj.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class HttpHelper {

	/**
	 * 获取请求Body
	 * @param request
	 * @return
	 */
	public static String getBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		try {
			inputStream = request.getInputStream();
			String charset = StringUtils.isNoneBlank(request.getCharacterEncoding()) ? request.getCharacterEncoding() : "UTF-8";
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(charset)));
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}