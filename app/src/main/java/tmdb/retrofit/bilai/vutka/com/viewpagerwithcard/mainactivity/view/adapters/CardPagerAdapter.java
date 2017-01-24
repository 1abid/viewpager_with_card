package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters;


import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model.CardItem;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp.MainMvp;

/**
 * Created by vutka bilai on 1/24/17.
 * mail : la4508@gmail.com
 */

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private MainMvp.ProvidedPresenterOps mPresenter;

    public CardPagerAdapter(MainMvp.ProvidedPresenterOps mPresenter) {
        this.mViews = new ArrayList<>();
        this.mData = new ArrayList<>();
        this.mPresenter = mPresenter;
    }


    public void addCardItem(){
        mViews.add(null);
        for (CardItem card: mPresenter.getCardItems()) {
            mData.add(card);
        }
    }

    @Override
    public float getBaseElevation() {
        return 0;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return null;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
