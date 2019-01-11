package hva.example.blackhorse.com.recipe_app.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hva.example.blackhorse.com.recipe_app.R;
import hva.example.blackhorse.com.recipe_app.Retrofit.RecipeJson;

/*
 * Created by Seyfullah Semen on 25-11-2018.
 */
public class RecipeFragment extends Fragment {

    private static final String TAG = RecipeFragment.class.getSimpleName();
    private Unbinder unbinder;

    private static final String ARG_RECIPE_ID = "arg_recipe_id";
    private RecipeJson mData;

    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.text_view_recipe_name)
    TextView mTextViewRecipeName;
    @BindView(R.id.text_view_recipe_description)
    TextView mTextViewRecipeDescription;


    public static RecipeFragment newInstance(RecipeJson recipeJson) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RECIPE_ID, recipeJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getArguments().getParcelable(ARG_RECIPE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View recipeView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, recipeView);
        unbinder = ButterKnife.bind(this, recipeView);
        mTextViewRecipeName.setText(mData.getTitle());
        mTextViewRecipeDescription.setText(mData.getRecipeId());
        Glide.with(this)
                .load(mData.getImageUrl())
                .into(mImageView);


        return recipeView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String loopThroughIngredients(List<String> ingredients) {
        StringBuilder ingredient = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredient.append("\t-\t").append(ingredients.get(i)).append("\n");
        }
        return ingredient.toString();
    }


}
