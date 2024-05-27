package com.nauroo.ppg.network;





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
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/news")
    Call<GenericResponse<List<NewsResponseModel>>> getNews();

    @GET("api/events")
    Call<GenericResponse<List<EventsResponseModel>>> getEvents();

    @GET("api/catalogs")
    Call<GenericResponse<CatalogsModel>> getCatalogs();

    @GET("api/product/{substratumId}/{temperatureId}/{durabilityId}/{environmentId}/{countryId}")
    Call<GenericResponse<List<SearchResultModel>>> getSearchResult(@Path("substratumId") String substratumId,
                                                                   @Path("temperatureId") String temperatureId,
                                                                   @Path("durabilityId") String durabilityId,
                                                                   @Path("environmentId") String environmentId,
                                                                   @Path("countryId") String countryId);

    @GET("api/segments")
    Call<GenericResponse<List<SegmentsModel>>> getSegments();

    @GET("api/countries")
    Call<GenericResponse<ArrayList<CountryModel>>> getCountry();

    @GET("api/cities/{countryId}")
    Call<GenericResponse<List<CityModel>>> getCity(@Path("countryId") String countryId);

    @POST("api/register")
    Call<GenericResponse<String>> register(@Body User user);

    @GET("api/manufacturers")
    Call<GenericResponse<List<ManufacturerModel>>> getManufacturer();

    @GET("api/categories/{manufacturerId}")
    Call<GenericResponse<List<CategoryModel>>> getCategory(@Path("manufacturerId")String manufacturerId);

    @GET("api/subcategories/{categoryId}")
    Call<GenericResponse<List<CategoryModel>>> getSubCategory(@Path("categoryId")String categoryId);

    @GET("api/competitors/{manufacturerId}/{categoryId}")
    Call<GenericResponse<List<ComparatorResultModel>>> getComparatorResult(@Path("manufacturerId")String manufacturerId,
                                                                           @Path("categoryId")String categoryId);

    @GET("api/competitors/{manufacturerId}/{categoryId}/{subCategoryId}")
    Call<GenericResponse<List<ComparatorResultModel>>> getComparator(@Path("manufacturerId")String manufacturerId,
                                                                     @Path("categoryId")String categoryId,@Path("subCategoryId") String subCategoryID);

    @GET("/api/advertisement")
    Call<GenericResponse<List<AdvertisementResponseModel>>> getAdvertisement();

    @GET("/api/competitors/{competitorId}")
    Call<GenericResponse<List<ComparatorResultModel>>> getCompetitors(@Path("competitorId")String competitorId);

}
