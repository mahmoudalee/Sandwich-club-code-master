package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView origin_tv;
    TextView description_tv;
    TextView ingredients_tv;
    TextView also_known_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        origin_tv = (TextView) findViewById(R.id.origin_tv);
        description_tv = (TextView) findViewById(R.id.description_tv);
        ingredients_tv =(TextView) findViewById(R.id.ingredients_tv);
        also_known_tv = (TextView) findViewById(R.id.also_known_tv);

        origin_tv.setText(sandwich.getPlaceOfOrigin());
        description_tv.setText(sandwich.getDescription());

//        List<String>temp_ingredients = sandwich.getIngredients();
//        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
//            ingredients_tv.append(temp_ingredients.get(i));
//        }
        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            ingredients_tv.append(sandwich.getIngredients().get(i));
        }

        for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
            also_known_tv.append(sandwich.getAlsoKnownAs().get(i));
        }

    }
}
