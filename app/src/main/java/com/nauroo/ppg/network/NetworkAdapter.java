package com.nauroo.ppg.network;

import android.content.Context;

import com.nauroo.ppg.model.AdvertisementResponseModel;
import com.nauroo.ppg.model.CatalogsModel;
import com.nauroo.ppg.model.CityModel;
import com.nauroo.ppg.model.ComparatorResultModel;
import com.nauroo.ppg.model.CountryModel;
import com.nauroo.ppg.model.EventsResponseModel;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.model.ManufacturerModel;
import com.nauroo.ppg.model.NewsResponseModel;
import com.nauroo.ppg.model.SearchResultModel;
import com.nauroo.ppg.model.SegmentsModel;
import com.nauroo.ppg.model.CategoryModel;
import com.nauroo.ppg.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Mohan M on 7/27/2017.
 */

public class NetworkAdapter {

    private static NetworkAdapter INSTANCE = null;
    private Context mContext;
    private ApiService mApiService;

    public static NetworkAdapter getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = new NetworkAdapter(context);
        }
        return INSTANCE;
    }

    private NetworkAdapter(Context context) {
        mContext = context;
        mApiService = new RestClient(context).getService();
    }

    public void getNews(Callback<GenericResponse<List<NewsResponseModel>>> callback){
        Call<GenericResponse<List<NewsResponseModel>>> call=mApiService.getNews();
        call.enqueue(callback);
    }

    public void getEvents(Callback<GenericResponse<List<EventsResponseModel>>> callback){
        Call<GenericResponse<List<EventsResponseModel>>> call=mApiService.getEvents();
        call.enqueue(callback);
    }

    public void getCatalogs(Callback<GenericResponse<CatalogsModel>> callback){
        Call<GenericResponse<CatalogsModel>> call=mApiService.getCatalogs();
        call.enqueue(callback);
    }

    public void getSearchResult(String substratumId, String temperatureId, String durabilityId, String environmentId, String countryId,Callback<GenericResponse<List<SearchResultModel>>> callback){
        Call<GenericResponse<List<SearchResultModel>>> call=mApiService.getSearchResult(substratumId,temperatureId,durabilityId,environmentId,countryId);
        call.enqueue(callback);
    }

    public void getSegments(Callback<GenericResponse<List<SegmentsModel>>> callback){
        Call<GenericResponse<List<SegmentsModel>>> call=mApiService.getSegments();
        call.enqueue(callback);
    }

    public void getCountry(Callback<GenericResponse<ArrayList<CountryModel>>> callback){
        Call<GenericResponse<ArrayList<CountryModel>>> call=mApiService.getCountry();
        call.enqueue(callback);
    }
    public void getCity(String countryId,Callback<GenericResponse<List<CityModel>>> callback){
        Call<GenericResponse<List<CityModel>>> call=mApiService.getCity(countryId);
        call.enqueue(callback);
    }
    public void register(User user, Callback<GenericResponse<String>> callback){
        Call<GenericResponse<String>> call=mApiService.register(user);
        call.enqueue(callback);
    }

    public void getManufacturer(Callback<GenericResponse<List<ManufacturerModel>>> callback){
        Call<GenericResponse<List<ManufacturerModel>>> call=mApiService.getManufacturer();
        call.enqueue(callback);
    }
    public void getCategory(String manufacturerId, Callback<GenericResponse<List<CategoryModel>>> callback){
        Call<GenericResponse<List<CategoryModel>>> call=mApiService.getCategory(manufacturerId);
        call.enqueue(callback);
    }
    public void getSubCategory(String categoryId, Callback<GenericResponse<List<CategoryModel>>> callback){
        Call<GenericResponse<List<CategoryModel>>> call=mApiService.getSubCategory(categoryId);
        call.enqueue(callback);
    }

    public void getComparatorResult(String manufacturerId,String categoryId,Callback<GenericResponse<List<ComparatorResultModel>>> callback){
        Call<GenericResponse<List<ComparatorResultModel>>> call=mApiService.getComparatorResult(manufacturerId,categoryId);
        call.enqueue(callback);
    }

    public void getComparator(String manufacturerId,String categoryId,String subCategoryId,Callback<GenericResponse<List<ComparatorResultModel>>> callback){
        Call<GenericResponse<List<ComparatorResultModel>>> call=mApiService.getComparator(manufacturerId,categoryId,subCategoryId);
        call.enqueue(callback);
    }

    public void getAdvertisement(Callback<GenericResponse<List<AdvertisementResponseModel>>> callback){
        Call<GenericResponse<List<AdvertisementResponseModel>>> call=mApiService.getAdvertisement();
        call.enqueue(callback);
    }
    public void getCompetitor(String competitorId,Callback<GenericResponse<List<ComparatorResultModel>>> callback){
        Call<GenericResponse<List<ComparatorResultModel>>> call=mApiService.getCompetitors(competitorId);
        call.enqueue(callback);
    }

}
