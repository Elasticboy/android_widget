package es.org.android_widget;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

	//private static final String ACTION_CLICK = "ACTION_CLICK";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		// Get ids of all the instances of the widget
		ComponentName widget = new ComponentName(context, MyWidgetProvider.class);
		int[] widgetIds = appWidgetManager.getAppWidgetIds(widget);
		
		for (int widgetId : widgetIds) {
			
			// Create some random data
			int number = (new Random().nextInt(100));
			final String data = String.valueOf(number);

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			Log.w("WidgetExample", data);
			
			// Set the text
			remoteViews.setTextViewText(R.id.textToUpdate, data);

			// Register an onClickListener
			Intent intent = new Intent(context, MyWidgetProvider.class);
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.textToUpdate, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
} 