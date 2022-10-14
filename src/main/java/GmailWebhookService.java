
import com.google.gson.JsonObject;
import model.WebhookBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GmailWebhookService {

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST(Configurations.googleChatWebhookUrl)
    Call<ResponseBody> alert(@Body WebhookBody body, @Query("thread_key") String thread_key);

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST(Configurations.googleChatWebhookUrl)
    Call<ResponseBody> alert(@Body JsonObject body, @Query("thread_key") String thread_key);

}
