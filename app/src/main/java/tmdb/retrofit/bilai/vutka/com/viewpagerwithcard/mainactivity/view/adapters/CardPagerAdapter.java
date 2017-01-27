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


    private MainMvp.ProvidedPresenterOps mPresenter;

    public CardPagerAdapter(MainMvp.ProvidedPresenterOps mPresenter) {

        this.mPresenter = mPresenter;
    }



    @Override
    public float getBaseElevation() {
        return mPresenter.getBaseElevation();
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mPresenter.getCardViewAt(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mPresenter.getCount();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return mPresenter.isViewFromObject(view , object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return mPresenter.instantiateItem(container , position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mPresenter.destroyItem(container , position , object);
    }
}
