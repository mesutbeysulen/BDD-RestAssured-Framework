package settergetter;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ThreadSafety {

    private static final ThreadLocal<Integer> statusCode =new ThreadLocal<>();
    private static final ThreadLocal<Response> res =new ThreadLocal<>();

    public static void setStatusCode(int code) {
        statusCode.set(code);
    }

    public static int getStatusCode() {
        return statusCode.get();
    }

    public static void unloadStatusCode() {
        statusCode.remove();
    }

    public static void setResponse(Response response) {
        res.set(response);
    }

    public static Response getResponse() {
        return res.get();
    }

    public static void unloadRes() {
        res.remove();
    }
}
