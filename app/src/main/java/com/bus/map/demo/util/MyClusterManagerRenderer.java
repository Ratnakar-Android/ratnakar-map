package com.bus.map.demo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.bus.map.demo.R;
import com.bus.map.demo.models.ClusterMarker;

public class MyClusterManagerRenderer extends DefaultClusterRenderer<ClusterMarker> {
    private IconGenerator iconGenerator;
    private ImageView imageview;
    private final int markerWidth;
    private final int  markerHeight;

    public MyClusterManagerRenderer(Context context, GoogleMap map, ClusterManager<ClusterMarker> clusterManager) {
        super(context, map, clusterManager);
        this.iconGenerator = iconGenerator;
        this.imageview = imageview;

        iconGenerator = new IconGenerator(context.getApplicationContext());
        imageview = new ImageView(context.getApplicationContext());
        markerWidth = (int)context.getResources().getDimension(R.dimen.custom_marker_image);
        markerHeight = (int)context.getResources().getDimension(R.dimen.custom_marker_image);
        imageview.setLayoutParams(new ViewGroup.LayoutParams(markerWidth, markerHeight));
        int padding = (int)context.getResources().getDimension(R.dimen.custom_marker_padding);
        imageview.setPadding(padding, padding, padding,padding);
        iconGenerator.setContentView(imageview);
    }

    @Override
    protected void onBeforeClusterItemRendered(ClusterMarker item, MarkerOptions markerOptions) {
        imageview.setImageResource(item.getIconPicture());
        Bitmap icon = iconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(item.getTitle());

    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<ClusterMarker> cluster) {
        return false;
    }
    /**
     * Update the GPS coordinate of a ClusterItem
     * @param clusterMarker
     */
    public void setUpdateMarker(ClusterMarker clusterMarker) {
        Marker marker = getMarker(clusterMarker);
        if (marker != null) {
            marker.setPosition(clusterMarker.getPosition());
        }
    }
}
