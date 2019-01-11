package hva.example.blackhorse.com.recipe_app.Retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Created by Seyfullah Semen on 25-11-2018.
 */
public class RecipeJson implements Parcelable {

    @SerializedName("ingredients")
    @Expose
    private List<String> ingredients = null;
    @SerializedName("recipe_id")
    @Expose
    private String recipeId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("title")
    @Expose
    private String title;

//    public RecipeJson(String title, String imageUrl, String recipeId, List<String> ingredients) {
//        this.imageUrl = imageUrl;
//        this.ingredients = ingredients;
//        this.recipeId = recipeId;
//        this.title = title;
//    }


    public String getRecipeId() {
        return recipeId;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.ingredients);
        dest.writeString(this.recipeId);
        dest.writeString(this.imageUrl);
        dest.writeString(this.title);
    }

    protected RecipeJson(Parcel in) {
        this.ingredients = in.createStringArrayList();
        this.recipeId = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
    }

    public static final Creator<RecipeJson> CREATOR = new Creator<RecipeJson>() {
        @Override
        public RecipeJson createFromParcel(Parcel source) {
            return new RecipeJson(source);
        }

        @Override
        public RecipeJson[] newArray(int size) {
            return new RecipeJson[size];
        }
    };
}
