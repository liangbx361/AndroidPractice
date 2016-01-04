package com.liangbx.android.practice.data.network;

import android.database.Observable;

import com.liangbx.android.practice.data.entity.ProductSearchResult;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Author liangbx
 * Date 2016/1/3
 */
public interface TaobaoApi {

    String API_BASE_URL = "https://suggest.taobao.com";

    @GET("/sug")
    Observable<ProductSearchResult> searchProduct(@Query("code") String code,
                                                  @Query("q") String keyword);
}
