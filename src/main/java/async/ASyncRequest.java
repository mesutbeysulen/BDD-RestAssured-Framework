package async;

import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import threadsafety.StatusCode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ASyncRequest {
    public static Response getResponse(String url) throws ExecutionException, InterruptedException {
        Future<Response> futureRes = Dsl.asyncHttpClient().prepareGet(url).execute();
        StatusCode.setStatusCode(futureRes.get().getStatusCode());
        return futureRes.get();
    }
}
