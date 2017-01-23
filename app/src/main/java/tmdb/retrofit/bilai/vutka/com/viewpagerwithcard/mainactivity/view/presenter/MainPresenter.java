package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.presenter;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.mvp.MainMvp;
import tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.utils.Toastmaker;

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


    public MainPresenter(MainMvp.RequiredViewOps mView) {
        this.mView = new WeakReference<MainMvp.RequiredViewOps>(mView);
    }


    /**
     * Called by View every time it is destroyed.
     *
     * @param isChangingConfiguration true: is changing configuration
     *                                 and will be recreated
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {

        //view should be null everytime onDestroy is called
        mView = null ;

        //inform model about the event
        mModel.onDestroy(isChangingConfiguration);

        //activity destroyed
        if(!isChangingConfiguration){
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




    /**
     * Called by Activity during MVP setup. Only called once.
     *
     * @param model Model instance
     */
    public void setModel(MainMvp.ProvidedModelOps model){
        mModel = model;
    }


    /**
     * Return the View reference
     * could throw exception if the view is unavailable
     * @return  MainMvp.RequiredViewOps
     * @throws NullPointerException
     */

    private MainMvp.RequiredViewOps getView() throws NullPointerException{

        if(mView != null){
            return mView.get();
        }else {
            throw new NumberFormatException("view is unavailable");
        }

    }


    private Toast makeToast(String msg){
        Toastmaker toast = new Toastmaker(getView().getActivityContxt());
        return toast.createToast(msg , 0);
    }

    @Override
    public Context getAppContext() {
        try{
             return getView().getAppContext();
        }catch (NullPointerException e){
            Log.e("Error " , e.getMessage());

            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try{
            return getView().getActivityContxt();
        }catch (NullPointerException e){
            Log.e("Error " , e.getMessage());

            return null;
        }
    }



}
