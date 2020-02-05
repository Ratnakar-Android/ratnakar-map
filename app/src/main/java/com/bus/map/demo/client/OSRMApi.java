package com.bus.map.demo.client;

import com.bus.map.demo.osrmmodels.OsrmItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OSRMApi {
    @GET("v1/driving/13.348860,52.517037;13.497634,52.529407;13.448555,52.523219")
    Single<Response<OsrmItem>> getOSRMRouteData();


}
