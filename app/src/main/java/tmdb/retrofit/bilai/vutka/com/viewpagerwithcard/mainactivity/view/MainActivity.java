package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.R;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.common.StateMaintainer;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model.MainModel;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp.MainMvp;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainMvp.RequiredViewOps{


    private Button fragmentButton;

    private MainMvp.ProvidedPresenterOps mPresenter;


    // Responsible to maintain the object's integrity
    // during configurations change
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer(MainActivity.class.getName() , getFragmentManager());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        setUpMVP();
    }


    private void setUpViews(){

        fragmentButton = (Button) findViewById(R.id.cardTypeBtn);

        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showStatus();
            }
        });
    }

    private void setUpMVP(){
        //check if statemainer has been created
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
    public void showToast(Toast toast) {
        toast.show();
    }
}
