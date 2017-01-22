package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.utils;


import android.content.Context;
import android.widget.Toast;

/**
 * Created by vutka bilai on 1/22/17.
 * mail : la4508@gmail.com
 */

public class Toastmaker {

    private Context mContext;

    public Toastmaker(Context mContext) {
        this.mContext = mContext;
    }

    public Toast createToast(String msg , int toastLenght){
        return Toast.makeText(mContext , msg , toastLenght);
    }
}
