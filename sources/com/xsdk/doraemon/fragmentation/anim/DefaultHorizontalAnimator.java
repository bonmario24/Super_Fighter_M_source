package com.xsdk.doraemon.fragmentation.anim;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.xsdk.doraemon.apkreflect.ReflectResource;

public class DefaultHorizontalAnimator extends FragmentAnimator implements Parcelable {
    public static final Parcelable.Creator<DefaultHorizontalAnimator> CREATOR = new Parcelable.Creator<DefaultHorizontalAnimator>() {
        public DefaultHorizontalAnimator createFromParcel(Parcel in) {
            return new DefaultHorizontalAnimator(in);
        }

        public DefaultHorizontalAnimator[] newArray(int size) {
            return new DefaultHorizontalAnimator[size];
        }
    };

    public DefaultHorizontalAnimator(Context context) {
        this.enter = ReflectResource.getInstance(context).getAnimId("h_fragment_enter");
        this.exit = ReflectResource.getInstance(context).getAnimId("h_fragment_exit");
        this.popEnter = ReflectResource.getInstance(context).getAnimId("h_fragment_pop_enter");
        this.popExit = ReflectResource.getInstance(context).getAnimId("h_fragment_pop_exit");
    }

    protected DefaultHorizontalAnimator(Parcel in) {
        super(in);
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
