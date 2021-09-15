package com.doraemon.p027eg;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: com.doraemon.eg.ReflectResource */
public class ReflectResource {
    private static String KP_RES_DEXPATH = "";
    private static String KP_RES_PACKAGE_NAME = "";
    private static boolean SOURCE_DEBUG = false;
    private static ReflectResource mInstance;
    public static ProxyContextImpl proxyContext;

    public static void setSourceDebug(boolean sourceDebug) {
        SOURCE_DEBUG = sourceDebug;
    }

    public static void setKpResPackageName(String kpResPackageName) {
        KP_RES_PACKAGE_NAME = kpResPackageName;
    }

    public static boolean isSourceDebug() {
        return SOURCE_DEBUG;
    }

    public static String getKpResPackageName() {
        return KP_RES_PACKAGE_NAME;
    }

    public static void setKpResDexpath(String kpResDexpath) {
        KP_RES_DEXPATH = kpResDexpath;
    }

    private ReflectResource(ProxyContextImpl contextImpl) {
        proxyContext = contextImpl;
    }

    public static ReflectResource getInstance(Context context) {
        if (mInstance == null || proxyContext == null) {
            synchronized (ReflectResource.class) {
                if (mInstance == null) {
                    mInstance = new ReflectResource(new ProxyContextImpl(context.getApplicationContext(), KP_RES_DEXPATH, SOURCE_DEBUG));
                }
            }
        }
        return mInstance;
    }

    public int getLayoutId(String layoutName) {
        String packageName = proxyContext.getPackageName();
        return proxyContext.getResources().getIdentifier(layoutName, "layout", proxyContext.getPackageName());
    }

    public View getLayoutView(String layoutName) {
        LayoutInflater inflater = (LayoutInflater) proxyContext.getSystemService("layout_inflater");
        if (getLayoutId(layoutName) == 0) {
            Log.e("error", "error inflater layoutName-->" + layoutName);
        }
        return inflater.inflate(proxyContext.getResources().getLayout(getLayoutId(layoutName)), (ViewGroup) null);
    }

    public int getWidgetViewID(String widgetName) {
        return proxyContext.getResources().getIdentifier(widgetName, "id", proxyContext.getPackageName());
    }

    public View getWidgetView(View layout, String widgetName) {
        return layout.findViewById(getWidgetViewID(widgetName));
    }

    public int getDrawableId(String imgName) {
        return proxyContext.getResources().getIdentifier(imgName, "drawable", proxyContext.getPackageName());
    }

    public Drawable getDrawable(String imgName) {
        return proxyContext.getResources().getDrawable(getDrawableId(imgName));
    }

    public int getStringId(String stringName) {
        return proxyContext.getResources().getIdentifier(stringName, "string", proxyContext.getPackageName());
    }

    public String getString(String stringName) {
        return proxyContext.getResources().getString(getStringId(stringName));
    }

    public int getAnimId(String animationName) {
        return proxyContext.getResources().getIdentifier(animationName, "anim", proxyContext.getPackageName());
    }

    public XmlPullParser getAnimXml(String animationName) {
        return proxyContext.getResources().getAnimation(getAnimId(animationName));
    }

    public XmlPullParser getAnimXml(int animationRes) {
        return proxyContext.getResources().getAnimation(animationRes);
    }

    public Animation getAnim(Context context, String animationName) {
        XmlPullParser parser = getAnimXml(animationName);
        try {
            return createAnimationFromXml(context, parser, (AnimationSet) null, Xml.asAttributeSet(parser));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Animation getAnim(Context context, int animRes) {
        XmlPullParser parser = getAnimXml(animRes);
        try {
            return createAnimationFromXml(context, parser, (AnimationSet) null, Xml.asAttributeSet(parser));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Animation createAnimationFromXml(Context c, XmlPullParser parser, AnimationSet parent, AttributeSet attrs) throws XmlPullParserException, IOException {
        Animation anim = null;
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if ((type != 3 || parser.getDepth() > depth) && type != 1) {
                if (type == 2) {
                    String name = parser.getName();
                    if (name.equals("set")) {
                        anim = new AnimationSet(c, attrs);
                        createAnimationFromXml(c, parser, (AnimationSet) anim, attrs);
                    } else if (name.equals("alpha")) {
                        anim = new AlphaAnimation(c, attrs);
                    } else if (name.equals("scale")) {
                        anim = new ScaleAnimation(c, attrs);
                    } else if (name.equals("rotate")) {
                        anim = new RotateAnimation(c, attrs);
                    } else if (name.equals("translate")) {
                        anim = new TranslateAnimation(c, attrs);
                    } else {
                        throw new RuntimeException("Unknown animation name: " + parser.getName());
                    }
                    if (parent != null) {
                        parent.addAnimation(anim);
                    }
                }
            }
        }
        return anim;
    }

    public int getColorId(String colorName) {
        return proxyContext.getResources().getIdentifier(colorName, "color", proxyContext.getPackageName());
    }

    public int getColor(String colorName) {
        return proxyContext.getResources().getColor(getColorId(colorName));
    }

    public int getDimensId(String dimenName) {
        return proxyContext.getResources().getIdentifier(dimenName, "dimen", proxyContext.getPackageName());
    }

    public float getDimens(String dimenName) {
        return proxyContext.getResources().getDimension(getDimensId(dimenName));
    }

    public int getStyleId(String dimenName) {
        return proxyContext.getResources().getIdentifier(dimenName, "styleable", proxyContext.getPackageName());
    }

    public int[] getIntArray(int styleid) {
        return proxyContext.getResources().getIntArray(styleid);
    }

    public Context getProxyContext() {
        return proxyContext;
    }
}
