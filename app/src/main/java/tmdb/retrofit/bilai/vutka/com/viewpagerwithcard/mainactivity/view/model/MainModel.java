package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model;


import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp.MainMvp;

/**
 * Created by vutka bilai on 1/22/17.
 * mail : la4508@gmail.com
 */

public class MainModel implements MainMvp.ProvidedModelOps {

    //presenter reference
    private MainMvp.RequiredPresenterOps mPresenter;

    public MainModel(MainMvp.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

        if(!isChangingConfiguration){

            mPresenter = null ;

        }
    }


}
