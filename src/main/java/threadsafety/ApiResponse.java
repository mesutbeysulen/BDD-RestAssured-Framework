package threadsafety;

import io.restassured.response.Response;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ApiResponse {

    private static final ThreadLocal<Response> res =new ThreadLocal<>();

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
