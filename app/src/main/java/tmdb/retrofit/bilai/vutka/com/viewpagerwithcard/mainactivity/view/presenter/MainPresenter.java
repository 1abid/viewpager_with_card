package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.presenter;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.R;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters.CardAdapter;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters.CardPagerAdapter;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model.CardItem;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp.MainMvp;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.utils.Toastmaker;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.views.ShadowTransformer;

/**
 * Created by vutka bilai on 1/22/17.
 * mail : la4508@gmail.com
 */

public class MainPresenter implements MainMvp.ProvidedPresenterOps, MainMvp.RequiredPresenterOps {

    // View reference. We use as a WeakReference
    // because the Activity could be destroyed at any time
    // and we don't want to create a memory leak
    private WeakReference<MainMvp.RequiredViewOps> mView;

    //model reference
    private MainMvp.ProvidedModelOps mModel;

    private ShadowTransformer mCardShadowTransformer;
    private CardPagerAdapter mCardAdapter;


    private float mBaseElevation;
    private List<CardView> mViews;
    private List<CardItem> mData;

    public MainPresenter(MainMvp.RequiredViewOps mView) {
        this.mView = new WeakReference<MainMvp.RequiredViewOps>(mView);


        this.mViews = new ArrayList<>();
        this.mData = new ArrayList<>();

    }


    /**
     * Called by View every time it is destroyed.
     *
     * @param isChangingConfiguration true: is changing configuration
     *                                and will be recreated
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {

        //view should be null everytime onDestroy is called
        mView = null;

        //inform model about the event
        mModel.onDestroy(isChangingConfiguration);

        //activity destroyed
        if (!isChangingConfiguration) {
            mModel = null;
        }
    }


    /**
     * Called by View during the reconstruction events
     *
     * @param view Activity instance
     */
    @Override
    public void setView(MainMvp.RequiredViewOps view) {
        mView = new WeakReference<MainMvp.RequiredViewOps>(view);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        getView().getShadowTransformer().enableScaling(b);
    }

    @Override
    public void addCardItem() {

        for (CardItem card : getCardItems()) {
            mViews.add(null);
            mData.add(card);
        }
    }


    public List<CardItem> getCardItems() {
        return mModel.generateCards();
    }


    /**
     * Called by Activity during MVP setup. Only called once.
     *
     * @param model Model instance
     */
    public void setModel(MainMvp.ProvidedModelOps model) {
        mModel = model;
    }

    /**
     * Return the View reference
     * could throw exception if the view is unavailable
     *
     * @return MainMvp.RequiredViewOps
     * @throws NullPointerException
     */

    private MainMvp.RequiredViewOps getView() throws NullPointerException {

        if (mView != null) {
            return mView.get();
        } else {
            throw new NumberFormatException("view is unavailable");
        }

    }


    private Toast makeToast(String msg) {
        Toastmaker toast = new Toastmaker(getView().getActivityContxt());
        return toast.createToast(msg, 0);
    }

    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            Log.e("Error ", e.getMessage());

            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContxt();
        } catch (NullPointerException e) {
            Log.e("Error ", e.getMessage());

            return null;
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * CardAdapter.MAX_ELEVATION_FACTOR);

        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }


    private void bind(CardItem item, View view) {
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        TextView contentTextView = (TextView) view.findViewById(R.id.contentTextView);
        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getText());
    }
}
