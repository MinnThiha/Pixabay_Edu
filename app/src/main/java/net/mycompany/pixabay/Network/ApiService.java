package net.mycompany.pixabay.Network;

import net.mycompany.pixabay.obj.PhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/")
    Call<PhotoResponse> getPhoto(@Query("key")String key, @Query("q")String findvalue);
}
