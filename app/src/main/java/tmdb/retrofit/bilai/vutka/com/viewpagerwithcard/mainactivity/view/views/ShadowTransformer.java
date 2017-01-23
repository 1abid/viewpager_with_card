package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.views;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters.CardAdapter;

/**
 * copied from com.github.rubensousa.viewpagercards
 */

public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager mViewPager;
    private CardAdapter mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled;

    public ShadowTransformer(ViewPager mViewPager, CardAdapter mAdapter) {
        this.mViewPager = mViewPager;
        mViewPager.addOnPageChangeListener(this);
        this.mAdapter = mAdapter;
    }


    public void enableScaling(boolean enable) {

        if (mScalingEnabled && !enable) {
            //shrink man card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());

            if (currentCard != null) {
                currentCard.animate().scaleY(1);
                currentCard.animate().scaleX(1);
            }
        }else if (!mScalingEnabled && enable){

            //grow the main card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard !=  null){
                currentCard.animate().scaleX(1.1f);
                currentCard.animate().scaleY(1.1f);
            }
        }


        mScalingEnabled = enable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        /**
         *
         * TODO : THIS IS SO DEEP I WILL SEE YOU IN THE MORNING
         *
         *
         *
         */


    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void transformPage(View page, float position) {

    }
}
