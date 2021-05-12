package threadsafety;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class StatusCode {
    private static final ThreadLocal<Integer> statusCode =new ThreadLocal<>();

    public static void setStatusCode(int code) {
        statusCode.set(code);
    }

    public static int getStatusCode() {
        return statusCode.get();
    }

    public static void unloadStatusCode() {
        statusCode.remove();
    }
}
