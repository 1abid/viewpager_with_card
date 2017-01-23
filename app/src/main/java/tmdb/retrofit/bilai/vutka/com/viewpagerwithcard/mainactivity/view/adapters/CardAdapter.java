package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters;

import android.support.v7.widget.CardView;

/**
 * Created by VutkaBilai on 1/23/17.
 * mail : la4508@gmail.com
 */

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
