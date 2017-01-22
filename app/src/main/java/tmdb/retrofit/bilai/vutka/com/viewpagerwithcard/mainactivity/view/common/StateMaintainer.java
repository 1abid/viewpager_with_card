package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.common;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by vutka bilai on 1/22/17.
 * mail : la4508@gmail.com
 */

public class StateMaintainer {

    protected final String TAG = getClass().getSimpleName();

    private final String mStateMaintainerTag;
    private final WeakReference<FragmentManager> mFragmentManager;
    private StatemngFragment mStateMaintainerFrag;
    private boolean isRecreating;


    public StateMaintainer(String mStateMaintainerTag, FragmentManager mFragmentManager) {
        this.mStateMaintainerTag = mStateMaintainerTag;
        this.mFragmentManager = new WeakReference<FragmentManager>(mFragmentManager);
    }

    /**
     * Creates the Fragment responsible to maintain the objects.
     *
     * @return true: fragment just created
     */
    public boolean isFirstTimeIn() {


        try {

            mStateMaintainerFrag = (StatemngFragment) mFragmentManager.get()
                    .findFragmentByTag(mStateMaintainerTag);

            if (mStateMaintainerFrag == null) {
                Log.d(TAG, "no saved fragment found to retained " + mStateMaintainerFrag);
                mStateMaintainerFrag = new StatemngFragment();
                mFragmentManager.get().beginTransaction().add(mStateMaintainerFrag, mStateMaintainerTag);

                isRecreating = false;


                return true;
            } else {

                Log.d(TAG, "saved fragment found , retaining fragment " + mStateMaintainerFrag);

                isRecreating = true;


                return false;
            }

        } catch (NullPointerException e) {

            Log.e(TAG, "Error " + e.getMessage());

            return false;
        }

    }

    /**
     * Return <strong>true</strong> it the current Activity was recreated at least one time
     * @return if the Activity was recreated
     */
    public boolean wasRecreated(){

        return isRecreating;
    }


    /**
     * insert the object to be preserved
     * @param key
     * @param obj
     */
    public void put(String key , Object obj){
       mStateMaintainerFrag.put(key , obj);
    }

    /**
     * insert the object to be preserved
     * @param obj
     */
    public void put(Object obj){
        mStateMaintainerFrag.put(obj.getClass().getName() , obj);
    }

    /**
     * get the saved object from stateMaintainerFrag
     * @param key
     * @param <T>
     * @return saved object
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key){
        return mStateMaintainerFrag.get(key);
    }


    public static class StatemngFragment extends Fragment {

        private HashMap<String, Object> mData = new HashMap<>();


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setRetainInstance(true);
        }


        /**
         * insert object in the hash map
         *
         * @param key
         * @param obj
         */
        public void put(String key, Object obj) {
            mData.put(key, obj);
        }


        /**
         * insert object in the hash map
         *
         * @param object object to be saved
         */
        public void put(Object object) {
            put(object.getClass().getName(), object);
        }


        /**
         * recovers saved object
         *
         * @param key
         * @param <T>
         * @return
         */
        @SuppressWarnings("unchecked")
        public <T> T get(String key) {
            return (T) mData.get(key);
        }
    }
}
