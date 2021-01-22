package Interfas;

import Retrofit.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface jsonPruebaApi {
    @GET("random_joke")
    Call<Post> getPost();
}
