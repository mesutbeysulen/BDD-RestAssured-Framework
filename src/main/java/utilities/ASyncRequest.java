package utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import settergetter.ThreadSafety;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ASyncRequest {
    public static Response getResponse(String url) throws ExecutionException, InterruptedException {
        Future<Response> futureRes = Dsl.asyncHttpClient().prepareGet(url).execute();
        ThreadSafety.setStatusCode(futureRes.get().getStatusCode());
        return futureRes.get();
    }
}
