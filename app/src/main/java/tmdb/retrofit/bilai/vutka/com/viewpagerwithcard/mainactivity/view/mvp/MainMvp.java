package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.List;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters.CardAdapter;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters.CardPagerAdapter;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model.CardItem;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.views.ShadowTransformer;

/**
 * Created by vutka bilai on 1/22/17.
 * mail : la4508@gmail.com
 */

public interface MainMvp {


    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     * (presenter -> view )
     */
    interface RequiredViewOps{

        Context getAppContext();
        Context getActivityContxt();

        ShadowTransformer getShadowTransformer();

        void showToast(Toast toast);

    }


    /**
     * Operations offered to View to communicate with Presenter.
     * Process user interaction, sends data requests to Model, etc.
     * (view -> presenter)
     */
    interface ProvidedPresenterOps{

        void onDestroy(boolean isChangingConfiguration);
        void setView(RequiredViewOps view);


        void onCheckedChanged(CompoundButton compoundButton, boolean b);

        void addCardItem();

        float getBaseElevation();
        CardView getCardViewAt(int position);
        int getCount();


        boolean isViewFromObject(View view, Object object);
        Object instantiateItem(ViewGroup container, int position);
        void destroyItem(ViewGroup container, int position, Object object);

    }


    /**
     * required Presenter operation available
     * to model (model -> presenter)
     */
    interface  RequiredPresenterOps{

        Context getAppContext();
        Context getActivityContext();
    }


    /**
     * Operations offered to model to communicate with presenter
     * Handles all data business logic
     * (presenter -> model )
     */
    interface ProvidedModelOps{

        void onDestroy(boolean isChangingConfiguration);

        List<CardItem> generateCards();

    }


}
