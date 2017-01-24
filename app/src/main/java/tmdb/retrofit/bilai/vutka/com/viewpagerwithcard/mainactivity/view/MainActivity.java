package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.R;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.adapters.CardPagerAdapter;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.common.StateMaintainer;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model.MainModel;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp.MainMvp;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.presenter.MainPresenter;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.views.ShadowTransformer;

public class MainActivity extends AppCompatActivity implements MainMvp.RequiredViewOps , CompoundButton.OnCheckedChangeListener{




    private MainMvp.ProvidedPresenterOps mPresenter;


    // Responsible to maintain the object's integrity
    // during configurations change
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer(MainActivity.class.getName() , getFragmentManager());

    private ViewPager mViewPager;
    private ShadowTransformer mCardShadowTransformer;
    private CardPagerAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
    }


    private void setUpViews(){

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ((CheckBox)findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);

        setUpMVP();


        /**
         * I just messed up here
         * this not view's job to adapter
         * TODO : move this shit to presenter it's his headache
         *
         */
        mCardAdapter = new CardPagerAdapter(mPresenter);
        mCardAdapter.addCardItem();
        mCardShadowTransformer = new ShadowTransformer(mViewPager , mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }



    private void setUpMVP(){
        //check if stateMainer has been created
        if(mStateMaintainer.isFirstTimeIn()){

            //create the  presenter
            MainPresenter presenter = new MainPresenter(this);

            //create the model
            MainModel model = new MainModel(presenter);

            //set presenter to model
            presenter.setModel(model);

            //add presenter and model to StateMaintainer
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);


            //set the presenter as interface
            //to limit the communication with it
            mPresenter = presenter;

        }else {

            mPresenter = mStateMaintainer.get(MainPresenter.class.getName());

            mPresenter.setView(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.onDestroy(isChangingConfigurations());
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContxt() {
        return this;
    }



    @Override
    public ShadowTransformer getShadowTransformer() {
        return mCardShadowTransformer;
    }



    @Override
    public void showToast(Toast toast) {
        toast.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mPresenter.onCheckedChanged(compoundButton , b);
    }
}
