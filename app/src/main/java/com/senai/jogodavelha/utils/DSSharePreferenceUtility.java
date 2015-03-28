package com.senai.jogodavelha.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noriaki on 01/02/2015.
 */
public class DSSharePreferenceUtility {

    private static final String PREFS_NAME = "MyPrefsFile";

    // Preferences
    public static SharedPreferences settings;

    /**
     * ApplicationStart/Main Event
     */
    public static final String SP_POSITION_VIEW_PAGER = "positionViewPage";

    // Methods

    // Remove
    public static void removeSharedPreferences(Context mContext, String key) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return;
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    // Setter
    public static void putStringTabSharedPreferences(Context mContext,
                                                     String key, String[] value) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return;
        }
        SharedPreferences.Editor editor = settings.edit();
        String tab = "";
        for (int i = 0; i < value.length; i++) {
            if (i == 0) {
                tab = value[i];
            } else {
                tab = tab + "#" + value[i];
            }
        }
        editor.putString(key, tab);
        editor.commit();
    }

    public static void putStringSharedPreferences(Context mContext, String key,
                                                  String value) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return;
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putIntSharedPreferences(Context mContext, String key,
                                               int value) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return;
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putLongSharedPreferences(Context mContext, String key,
                                                long value) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return;
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void putBooleanSharedPreferences(Context mContext,
                                                   String key, boolean value) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return;
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void putFloatSharedPreferences(Context mContext, String key,
                                                 float value) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return;
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    // Getter
    public static List<CharSequence> getListSharedPreferences(Context mContext,
                                                              String key) {
        return getListSharedPreferences(mContext, key, null);
    }

    public static List<CharSequence> getListSharedPreferences(Context mContext,
                                                              String key, String[] defaultValue) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return null;
        }
        final List<CharSequence> mList = new ArrayList<CharSequence>();
        final String mTab = settings.getString(key, "");

        if (!TextUtils.isEmpty(mTab)) {
            final String[] mTabs = mTab.split("#");
            for (int i = 0; i < mTabs.length; i++) {
                mList.add(mTabs[i].toString());
            }
        }
        return mList;
    }

    public static String getStringSharedPreferences(Context mContext, String key) {
        return getStringSharedPreferences(mContext, key, "");
    }

    public static String getStringSharedPreferences(Context mContext,
                                                    String key, String defaultValue) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return defaultValue;
        } else {
            return settings.getString(key, defaultValue);
        }
    }

    public static int getIntSharedPreferences(Context mContext, String key) {
        return getIntSharedPreferences(mContext, key, -1);
    }

    public static int getIntSharedPreferences(Context mContext, String key,
                                              int defaultValue) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return defaultValue;
        } else {
            try {
                return settings.getInt(key, defaultValue);
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static long getLongSharedPreferences(Context mContext, String key) {
        return getLongSharedPreferences(mContext, key, -1);
    }

    public static long getLongSharedPreferences(Context mContext, String key,
                                                long defaultValue) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return -1L;
        } else {
            return settings.getLong(key, defaultValue);
        }
    }

    public static boolean getBooleanSharedPreferences(Context mContext,
                                                      String key) {
        return getBooleanSharedPreferences(mContext, key, false);
    }

    public static boolean getBooleanSharedPreferences(Context mContext,
                                                      String key, boolean defaultValue) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return defaultValue;
        }
        return settings.getBoolean(key, defaultValue);
    }

    public static float getFloatSharedPreferences(Context mContext, String key) {
        return getFloatSharedPreferences(mContext, key, -1);
    }

    public static float getFloatSharedPreferences(Context mContext, String key,
                                                  float defaultValue) {
        SharedPreferences settings = getSharedPreferences(mContext);
        if (settings == null) {
            return -1F;
        }
        return settings.getFloat(key, defaultValue);
    }

    private static SharedPreferences getSharedPreferences(Context mContext) {
        if (settings != null) {
            return settings;
        } else if (mContext != null) {
            return mContext.getSharedPreferences(PREFS_NAME, 0);
        } else {
            return null;
        }
    }

    public static void writeObjectToFile(Context context, Object object,
                                         String filename) {

        ObjectOutputStream objectOut = null;
        try {

            FileOutputStream fileOut = context.openFileOutput(filename,
                    Activity.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            fileOut.getFD().sync();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
        }
    }

    /**
     * @param context
     * @param filename
     * @return
     */
    public static Object readObjectFromFile(Context context, String filename) {

        ObjectInputStream objectIn = null;
        Object object = null;
        try {

            FileInputStream fileIn = context.getApplicationContext()
                    .openFileInput(filename);
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();

        } catch (FileNotFoundException e) {
            // Do nothing
            return object;
        } catch (IOException e) {
            e.printStackTrace();
            return object;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return object;
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
        }

        return object;
    }
}

