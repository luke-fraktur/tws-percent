package com.luke.earbudswidget;

import android.app.AlarmManager; import android.app.PendingIntent; import android.appwidget.AppWidgetManager; import android.appwidget.AppWidgetProvider; import android.bluetooth.BluetoothAdapter; import android.bluetooth.BluetoothDevice; import android.content.*; import android.content.pm.PackageManager; import android.graphics.Color; import android.os.Build; import android.os.SystemClock; import android.widget.RemoteViews; import java.lang.reflect.Method; import java.util.Set;

public class EarbudsWidgetProvider extends AppWidgetProvider {
    private static final String ACTION_REFRESH = "com.luke.earbudswidget.REFRESH";
    private static final long REFRESH_INTERVAL_MS = 5 * 60 * 1000L;
    @Override public void onUpdate(Context c, AppWidgetManager m, int[] ids) { update(c,m,ids); scheduleRefresh(c); }
    @Override public void onEnabled(Context c) { scheduleRefresh(c); }
    @Override public void onDisabled(Context c) { cancelRefresh(c); }
    @Override public void onReceive(Context c, Intent i) { super.onReceive(c,i); String action=i.getAction(); if (ACTION_REFRESH.equals(action) || Intent.ACTION_BOOT_COMPLETED.equals(action)) { updateAll(c); scheduleRefresh(c); } }
    public static void updateAll(Context c) { AppWidgetManager m=AppWidgetManager.getInstance(c); update(c,m,m.getAppWidgetIds(new ComponentName(c,EarbudsWidgetProvider.class))); }
    private static void scheduleRefresh(Context c) { AlarmManager alarm=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE); if(alarm==null) return; PendingIntent p=refreshPendingIntent(c); alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+REFRESH_INTERVAL_MS, REFRESH_INTERVAL_MS, p); }
    private static void cancelRefresh(Context c) { AlarmManager alarm=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE); if(alarm!=null) alarm.cancel(refreshPendingIntent(c)); }
    private static PendingIntent refreshPendingIntent(Context c) { Intent in=new Intent(c,EarbudsWidgetProvider.class).setAction(ACTION_REFRESH); return PendingIntent.getBroadcast(c, 9001, in, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE); }
    private static void update(Context c, AppWidgetManager m, int[] ids) { int level=findBattery(c); int color=batteryColor(level); for(int id:ids){ RemoteViews v=new RemoteViews(c.getPackageName(), R.layout.widget_earbuds); v.setTextViewText(R.id.widget_percent, level>=0 ? level+"%" : "--%"); v.setTextColor(R.id.widget_percent, color); Intent in=new Intent(c,EarbudsWidgetProvider.class).setAction(ACTION_REFRESH); v.setOnClickPendingIntent(R.id.widget_root, PendingIntent.getBroadcast(c, id, in, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE)); m.updateAppWidget(id,v); } }
    private static int batteryColor(int level) { if(level < 0) return Color.WHITE; if(level >= 70) return Color.rgb(74, 222, 128); if(level >= 30) return Color.rgb(250, 204, 21); return Color.rgb(248, 113, 113); }
    private static int findBattery(Context c) { if(Build.VERSION.SDK_INT>=31 && c.checkSelfPermission("android.permission.BLUETOOTH_CONNECT")!=PackageManager.PERMISSION_GRANTED) return -1; BluetoothAdapter a=BluetoothAdapter.getDefaultAdapter(); if(a==null || !a.isEnabled()) return -1; try { Set<BluetoothDevice> ds=a.getBondedDevices(); int best=-1; for(BluetoothDevice d:ds){ try { Method x=d.getClass().getMethod("getBatteryLevel"); Object o=x.invoke(d); if(o instanceof Integer && (Integer)o>=0) best=Math.max(best,(Integer)o); } catch(Exception ignored){} } return best; } catch(SecurityException e){return -1;} }
}
