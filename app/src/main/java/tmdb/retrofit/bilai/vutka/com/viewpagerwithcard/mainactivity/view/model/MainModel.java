package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model;


import java.util.ArrayList;
import java.util.List;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.R;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp.MainMvp;

/**
 * Created by vutka bilai on 1/22/17.
 * mail : la4508@gmail.com
 */

public class MainModel implements MainMvp.ProvidedModelOps {

    //presenter reference
    private MainMvp.RequiredPresenterOps mPresenter;

    private List<CardItem> cards;

    public MainModel(MainMvp.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;

        cards = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

        if(!isChangingConfiguration){

            mPresenter = null ;

        }
    }

    @Override
    public List<CardItem> generateCards() {

        CardItem cardItemOne = new CardItem(R.string.title_1 , R.string.text_1);
        cards.add(cardItemOne);

        CardItem cardItemTwo = new CardItem(R.string.title_2 , R.string.text_1);
        cards.add(cardItemTwo);

        CardItem cardItemThree = new CardItem(R.string.title_3 , R.string.text_1);
        cards.add(cardItemThree);

        return cards;
    }


}
