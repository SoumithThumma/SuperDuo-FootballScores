package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;

public class FootballWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Cursor cursor = context.getContentResolver().query(DatabaseContract.BASE_CONTENT_URI, null, null, null, null);
        if (!cursor.moveToLast()) {
            return;
        }
        String hometeam = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.HOME_COL));
        String awayteam = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_COL));
        String time = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.TIME_COL));
        String homegoals = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.HOME_GOALS_COL));
        String awaygoals = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_GOALS_COL));
        String score = homegoals + " - " + awaygoals;

        for (int appWidgetId : appWidgetIds) {

            RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget);
            view.setTextViewText(R.id.home_name, hometeam);
            view.setTextViewText(R.id.away_name, awayteam);

            view.setTextViewText(R.id.score_textview, score);
            view.setTextViewText(R.id.data_textview, time);

            appWidgetManager.updateAppWidget(appWidgetId, view);

        }


    }

}