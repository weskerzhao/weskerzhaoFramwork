package com.waterbase.utile;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * PreferencesManager管理类，提供get和put方法来重写SharedPreferences所提供的方法
 */
public class PreferencesManager {

    private final String tag = PreferencesManager.class.getSimpleName();
    private Context mContext;
    private SharedPreferences preferences;
    // 默认系统SharedPreferences KEY值名称
    private static String shareName = "PreferencesManager";
    public static final String THEME = "Theme";
    public static final String LANG = "Lang";
    private static PreferencesManager instance;

    /**
     * 构造方法
     *
     * @param context
     */
    private PreferencesManager(Context context) {
        this(context, shareName);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param shareName
     */
    private PreferencesManager(Context context, String shareName) {
        mContext = context;
        preferences = context.getSharedPreferences(shareName, Context.MODE_PRIVATE);
    }

    public static PreferencesManager getInstance(Context context) {
        return getInstance(context, shareName);
    }

    public static PreferencesManager getInstance(Context context, String shareName) {
        if (instance == null) {
            synchronized (PreferencesManager.class) {
                if (instance == null) {
                    instance = new PreferencesManager(context, shareName);
                }
            }
        }
        return instance;
    }

    public void put(String key, boolean value) {
        Editor edit = preferences.edit();
        if (edit != null) {
            edit.putBoolean(key, value);
            edit.apply();
        }
    }

    public void put(String key, String value) {
        Editor edit = preferences.edit();
        if (edit != null) {
            edit.putString(key, value);
            edit.apply();
        }
    }

    public void put(String key, int value) {
        Editor edit = preferences.edit();
        if (edit != null) {
            edit.putInt(key, value);
            edit.apply();
        }
    }

    public void put(String key, float value) {
        Editor edit = preferences.edit();
        if (edit != null) {
            edit.putFloat(key, value);
            edit.apply();
        }
    }

    public void put(String key, long value) {
        Editor edit = preferences.edit();
        if (edit != null) {
            edit.putLong(key, value);
            edit.apply();
        }
    }

    public void put(String key, Set<String> value) {
        Editor edit = preferences.edit();
        if (edit != null) {
            edit.putStringSet(key, value);
            edit.apply();
        }
    }

    public void put(String key, Object object) {
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            // 然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            Editor edit = preferences.edit();
            if (edit != null) {
                edit.putString(key, objectVal);
                edit.apply();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String get(String key) {
        return preferences.getString(key, "");
    }

    public String get(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    // 移除key
    public void remove(String key) {
        Editor edit = preferences.edit();
        if (edit != null) {
            edit.remove(key);
            edit.apply();
        }
    }

    public boolean get(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public int get(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public float get(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public long get(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public Set<String> get(String key, Set<String> defValue) {
        return preferences.getStringSet(key, defValue);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        if (preferences.contains(key)) {
            String objectVal = preferences.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            // 一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public int getTheme(int defThemeId) {
        return instance.get(THEME, defThemeId);
    }

    public void setTheme(int themeId) {
        instance.put(THEME, themeId);
    }

    public String getLanguage(String defLang) {
        return instance.get(LANG, defLang);
    }

    public void setLang(String Language) {
        instance.put(LANG, Language);
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }

    /**
     * 保存List 可以叠加保存
     *
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(Context context, String tag, List<T> datalist) {
        PreferencesManager instance = PreferencesManager.getInstance(context);
        List<T> tagList = instance.getDataList(tag);
        if (null == datalist || datalist.size() <= 0) {
            return;
        } else if (tagList.size() > 0) {
            for (int i = 0; i < tagList.size(); i++) {
                T t = tagList.get(i);
                //不存重复的
                if (!datalist.get(0).equals(t)) {
                    datalist.add(t);
                }
            }
        }

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        Editor edit = preferences.edit();
        edit.clear();
        edit.putString(tag, strJson);
        edit.apply();

    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag) {
        List<T> datalist = new ArrayList<T>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;

    }
}
