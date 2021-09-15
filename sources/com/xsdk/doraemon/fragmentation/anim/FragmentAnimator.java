package com.xsdk.doraemon.fragmentation.anim;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.AnimRes;

public class FragmentAnimator implements Parcelable {
    public static final Parcelable.Creator<FragmentAnimator> CREATOR = new Parcelable.Creator<FragmentAnimator>() {
        public FragmentAnimator createFromParcel(Parcel in) {
            return new FragmentAnimator(in);
        }

        public FragmentAnimator[] newArray(int size) {
            return new FragmentAnimator[size];
        }
    };
    @AnimRes
    protected int enter;
    @AnimRes
    protected int exit;
    @AnimRes
    protected int popEnter;
    @AnimRes
    protected int popExit;

    public FragmentAnimator() {
    }

    public FragmentAnimator(int enter2, int exit2) {
        this.enter = enter2;
        this.exit = exit2;
    }

    public FragmentAnimator(int enter2, int exit2, int popEnter2, int popExit2) {
        this.enter = enter2;
        this.exit = exit2;
        this.popEnter = popEnter2;
        this.popExit = popExit2;
    }

    public FragmentAnimator copy() {
        return new FragmentAnimator(getEnter(), getExit(), getPopEnter(), getPopExit());
    }

    protected FragmentAnimator(Parcel in) {
        this.enter = in.readInt();
        this.exit = in.readInt();
        this.popEnter = in.readInt();
        this.popExit = in.readInt();
    }

    public int getEnter() {
        return this.enter;
    }

    public FragmentAnimator setEnter(int enter2) {
        this.enter = enter2;
        return this;
    }

    public int getExit() {
        return this.exit;
    }

    public FragmentAnimator setExit(int exit2) {
        this.exit = exit2;
        return this;
    }

    public int getPopEnter() {
        return this.popEnter;
    }

    public FragmentAnimator setPopEnter(int popEnter2) {
        this.popEnter = popEnter2;
        return this;
    }

    public int getPopExit() {
        return this.popExit;
    }

    public FragmentAnimator setPopExit(int popExit2) {
        this.popExit = popExit2;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.enter);
        dest.writeInt(this.exit);
        dest.writeInt(this.popEnter);
        dest.writeInt(this.popExit);
    }
}
