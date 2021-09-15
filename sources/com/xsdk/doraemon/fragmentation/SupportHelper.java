package com.xsdk.doraemon.fragmentation;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentationMagician;
import java.util.ArrayList;
import java.util.List;

public class SupportHelper {
    private static final long SHOW_SPACE = 200;

    private SupportHelper() {
    }

    public static void showSoftInput(final View view) {
        if (view != null && view.getContext() != null) {
            final InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService("input_method");
            view.requestFocus();
            view.postDelayed(new Runnable() {
                public void run() {
                    imm.showSoftInput(view, 2);
                }
            }, SHOW_SPACE);
        }
    }

    public static void hideSoftInput(View view) {
        if (view != null && view.getContext() != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showFragmentStackHierarchyView(ISupportActivity support) {
        support.getSupportDelegate().showFragmentStackHierarchyView();
    }

    public static void logFragmentStackHierarchy(ISupportActivity support, String TAG) {
        support.getSupportDelegate().logFragmentStackHierarchy(TAG);
    }

    public static ISupportFragment getTopFragment(FragmentManager fragmentManager) {
        return getTopFragment(fragmentManager, 0);
    }

    public static ISupportFragment getTopFragment(FragmentManager fragmentManager, int containerId) {
        List<Fragment> fragmentList = FragmentationMagician.getActiveFragments(fragmentManager);
        if (fragmentList == null) {
            return null;
        }
        for (int i = fragmentList.size() - 1; i >= 0; i--) {
            Fragment fragment = fragmentList.get(i);
            if (fragment instanceof ISupportFragment) {
                ISupportFragment iFragment = (ISupportFragment) fragment;
                if (containerId == 0 || containerId == iFragment.getSupportDelegate().mContainerId) {
                    return iFragment;
                }
            }
        }
        return null;
    }

    public static ISupportFragment getPreFragment(Fragment fragment) {
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager == null) {
            return null;
        }
        List<Fragment> fragmentList = FragmentationMagician.getActiveFragments(fragmentManager);
        if (fragmentList == null) {
            return null;
        }
        for (int i = fragmentList.indexOf(fragment) - 1; i >= 0; i--) {
            Fragment preFragment = fragmentList.get(i);
            if (preFragment instanceof ISupportFragment) {
                return (ISupportFragment) preFragment;
            }
        }
        return null;
    }

    public static <T extends ISupportFragment> T findFragment(FragmentManager fragmentManager, Class<T> fragmentClass) {
        return findStackFragment(fragmentClass, (String) null, fragmentManager);
    }

    public static <T extends ISupportFragment> T findFragment(FragmentManager fragmentManager, String fragmentTag) {
        return findStackFragment((Class) null, fragmentTag, fragmentManager);
    }

    public static ISupportFragment getActiveFragment(FragmentManager fragmentManager) {
        return getActiveFragment(fragmentManager, (ISupportFragment) null);
    }

    static <T extends ISupportFragment> T findStackFragment(Class<T> fragmentClass, String toFragmentTag, FragmentManager fragmentManager) {
        Fragment fragment = null;
        if (toFragmentTag == null) {
            List<Fragment> fragmentList = FragmentationMagician.getActiveFragments(fragmentManager);
            if (fragmentList != null) {
                int i = fragmentList.size() - 1;
                while (true) {
                    if (i < 0) {
                        break;
                    }
                    Fragment brotherFragment = fragmentList.get(i);
                    if ((brotherFragment instanceof ISupportFragment) && brotherFragment.getClass().getName().equals(fragmentClass.getName())) {
                        fragment = brotherFragment;
                        break;
                    }
                    i--;
                }
            } else {
                return null;
            }
        } else {
            fragment = fragmentManager.findFragmentByTag(toFragmentTag);
            if (fragment == null) {
                return null;
            }
        }
        return (ISupportFragment) fragment;
    }

    private static ISupportFragment getActiveFragment(FragmentManager fragmentManager, ISupportFragment parentFragment) {
        List<Fragment> fragmentList = FragmentationMagician.getActiveFragments(fragmentManager);
        if (fragmentList == null) {
            return parentFragment;
        }
        for (int i = fragmentList.size() - 1; i >= 0; i--) {
            Fragment fragment = fragmentList.get(i);
            if ((fragment instanceof ISupportFragment) && fragment.isResumed() && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                return getActiveFragment(fragment.getChildFragmentManager(), (ISupportFragment) fragment);
            }
        }
        return parentFragment;
    }

    public static ISupportFragment getBackStackTopFragment(FragmentManager fragmentManager) {
        return getBackStackTopFragment(fragmentManager, 0);
    }

    public static ISupportFragment getBackStackTopFragment(FragmentManager fragmentManager, int containerId) {
        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(i).getName());
            if (fragment instanceof ISupportFragment) {
                ISupportFragment supportFragment = (ISupportFragment) fragment;
                if (containerId == 0 || containerId == supportFragment.getSupportDelegate().mContainerId) {
                    return supportFragment;
                }
            }
        }
        return null;
    }

    static <T extends ISupportFragment> T findBackStackFragment(Class<T> fragmentClass, String toFragmentTag, FragmentManager fragmentManager) {
        int count = fragmentManager.getBackStackEntryCount();
        if (toFragmentTag == null) {
            toFragmentTag = fragmentClass.getName();
        }
        for (int i = count - 1; i >= 0; i--) {
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
            if (toFragmentTag.equals(entry.getName())) {
                Fragment fragment = fragmentManager.findFragmentByTag(entry.getName());
                if (fragment instanceof ISupportFragment) {
                    return (ISupportFragment) fragment;
                }
            }
        }
        return null;
    }

    static List<Fragment> getWillPopFragments(FragmentManager fm, String targetTag, boolean includeTarget) {
        Fragment target = fm.findFragmentByTag(targetTag);
        List<Fragment> willPopFragments = new ArrayList<>();
        List<Fragment> fragmentList = FragmentationMagician.getActiveFragments(fm);
        if (fragmentList != null) {
            int size = fragmentList.size();
            int startIndex = -1;
            int i = size - 1;
            while (true) {
                if (i < 0) {
                    break;
                } else if (target != fragmentList.get(i)) {
                    i--;
                } else if (includeTarget) {
                    startIndex = i;
                } else if (i + 1 < size) {
                    startIndex = i + 1;
                }
            }
            if (startIndex != -1) {
                for (int i2 = size - 1; i2 >= startIndex; i2--) {
                    Fragment fragment = fragmentList.get(i2);
                    if (!(fragment == null || fragment.getView() == null)) {
                        willPopFragments.add(fragment);
                    }
                }
            }
        }
        return willPopFragments;
    }
}
