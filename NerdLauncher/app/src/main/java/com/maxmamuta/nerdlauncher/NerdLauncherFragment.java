package com.maxmamuta.nerdlauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Maxim on 2/20/2017.
 */

public class NerdLauncherFragment extends ListFragment {
    private static final String TAG = "NerdLauncherFragment";
    private PackageManager pm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        pm = getActivity().getPackageManager();
        List<ResolveInfo>activities = pm.queryIntentActivities(startupIntent, 0);

        Log.i(TAG, "I've found "+activities.size() + " activities.");

        Collections.sort(activities, new Comparator<ResolveInfo>() {
            public int compare(ResolveInfo a, ResolveInfo b) {
                PackageManager pm = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(
                        a.loadIcon(pm).toString(),
                        b.loadIcon(pm).toString());
            }
        });

        ArrayAdapter<ResolveInfo> adapter = new ArrayAdapter<ResolveInfo>(
                getActivity(), R.layout.listview, activities) {
            public View getView(int pos, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.listview, null);
                }

                TextView tv = (TextView) convertView.findViewById(R.id.textView);
                ResolveInfo ri = getItem(pos);
                tv.setText(ri.loadLabel(pm));

                ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
                iv.setImageDrawable(ri.loadIcon(pm));
               // TextView tv = (TextView)v;
               // ResolveInfo ri = getItem(pos);
               // tv.setText(ri.loadLabel(pm));
                return convertView;
            }
        };

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ResolveInfo resolveInfo = (ResolveInfo) l.getAdapter().getItem(position);
        ActivityInfo activityInfo = resolveInfo.activityInfo;

        if(activityInfo == null) {
            return;
        }

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
