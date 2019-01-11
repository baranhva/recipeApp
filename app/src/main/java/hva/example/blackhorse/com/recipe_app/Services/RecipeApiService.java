package hva.example.blackhorse.com.recipe_app.Services;

import hva.example.blackhorse.com.recipe_app.RecipeRespond;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/*
 * Created by Seyfullah Semen on 25-11-2018.
 */
public interface RecipeApiService {

    String BASE_URL = "https://www.food2fork.com/";

    /**
     * Create a retrofit client.
     */

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

//    api/get/?key=861881ba45b7551aed614de00d1295a2&rId=35382
    @GET("/api/search?key=861881ba45b7551aed614de00d1295a2&rId=35382&sort=r&count=3")
    Call<RecipeRespond> getRecipe();
}
