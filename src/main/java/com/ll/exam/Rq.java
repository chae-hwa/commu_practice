package com.ll.exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }

    public String getParam(String paramName, String defaultValue) {
        String value = req.getParameter(paramName);

        if (value == null || value.trim().length() == 0) {
            return defaultValue;
        }

        return value;
    }

    public int getIntParam(String paramName, int defaultValue) {
        String value = req.getParameter(paramName);

        if (value == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void appendBody(String str) {
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAttr(String name, Object value) {
        req.setAttribute(name, value);
    }

    public void view(String path) {
        // gugudan2.jsp 에게 나머지 작업을 토스
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/" + path + ".jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPath() {
        return req.getRequestURI();
    }

    public String getActionPath() {
        String[] bits = req.getRequestURI().split("/");

        return "/%s/%s/%s".formatted(bits[1], bits[2], bits[3]);
    }

    public String getMethod() {
        return req.getMethod();
    }

    // url의 마지막 번호를 구분하기.
    public long getLongPathValueByIndex(int index, long defaultValue) {
        String value = getPathValueByIndex(index, null);

        if ( value == null ) {
            return defaultValue;
        }

        try {
            return Long.parseLong(value);  // long으로 형변환하여 반환해라라
       }
        catch ( NumberFormatException e ) {
            return defaultValue;
        }
    }
    // url 쪼개서 마지막 숫자를 찾아내라
    public String getPathValueByIndex(int index, String defaultValue) { //우리 프로그램에서 index는 1로만 적용
        String[] bits = req.getRequestURI().split("/");

        try {
            return bits[4 + index]; // 4번째 + 1 -> 5번째 값을 반환해라.
        } catch (ArrayIndexOutOfBoundsException e) {
            return defaultValue;
        }
    }
}