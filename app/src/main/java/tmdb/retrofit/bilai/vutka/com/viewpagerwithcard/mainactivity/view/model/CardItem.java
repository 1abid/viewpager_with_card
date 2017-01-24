package tmdb.retrofit.bilai.vutka.com.viewpagerwithcard.mainactivity.view.model;


/**
 * Created by vutka bilai on 1/24/17.
 * mail : la4508@gmail.com
 */

public class CardItem {

    private int mTextResource;
    private int MTitleResource;

    public CardItem(int mTextResource, int MTitleResource) {
        this.mTextResource = mTextResource;
        this.MTitleResource = MTitleResource;
    }

    public int getmTextResource() {
        return mTextResource;
    }

    public int getMTitleResource() {
        return MTitleResource;
    }
}
