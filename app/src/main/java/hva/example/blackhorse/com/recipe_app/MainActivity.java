package hva.example.blackhorse.com.recipe_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hva.example.blackhorse.com.recipe_app.Fragments.RecipeFragment;
import hva.example.blackhorse.com.recipe_app.Retrofit.RecipeJson;
import hva.example.blackhorse.com.recipe_app.Services.RecipeApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<RecipeJson> recipes = new ArrayList<>();
    private RecipeApiService mRecipeApiService;

    private PagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.container)
    ViewPager mPager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        requestData();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), recipes);
        mPager.setAdapter(mSectionsPagerAdapter);


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<RecipeJson> recipes;

        public SectionsPagerAdapter(FragmentManager fm, List<RecipeJson> recipes) {
            super(fm);
            this.recipes = recipes;
        }

        @Override
        public Fragment getItem(int position) {

            return RecipeFragment.newInstance(recipes.get(position));
        }

        @Override
        public int getCount() {
            return recipes.size();
        }
    }

    private void requestData() {
        RecipeApiService service = RecipeApiService.retrofit.create(RecipeApiService.class);
        Call<RecipeRespond> call = service.getRecipe();
        call.enqueue(new Callback<RecipeRespond>() {
            @Override
            public void onResponse(Call<RecipeRespond> call, Response<RecipeRespond> response) {
                if (response.body() != null) {
                    recipes.addAll(Arrays.asList(response.body().recipes));

                    mSectionsPagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<RecipeRespond> call, Throwable t) {
                Log.i(TAG, "Something went wrong: " + t.getMessage());
            }

        });
    }


}
