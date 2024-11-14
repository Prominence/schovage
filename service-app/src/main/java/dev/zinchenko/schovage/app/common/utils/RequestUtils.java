package dev.zinchenko.schovage.app.common.utils;

import jakarta.servlet.http.HttpServletRequest;

public final class RequestUtils {
    private RequestUtils() {}

    public static String getRequesterIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && !ip.isEmpty()) {
            return ip.split(",")[0];
        } else {
            return request.getRemoteAddr();
        }
    }
}
