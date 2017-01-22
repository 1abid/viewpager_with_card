package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp;


import android.content.Context;
import android.widget.Toast;

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
        void showStatus();
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

        String getDemoString();

    }


}
