package com.entertainmentapp.network


import com.entertainmentapp.view.ui.registration.data.model.RegistrationResponsePojo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface iApiService {

    @GET("Login/ValidateLogin?")
    fun validateUser(
        @Query(encoded = true, value = "firstName") firstName: String?,
        @Query(encoded = true, value = "lastName")
        lastName: String?,
        @Query(encoded = true, value = "mobileNumber")
        mobileNumber: String?,
        @Query(encoded = true, value = "email")
        email: String?,
        @Query(encoded = true, value = "Password")
        Password: String?,
        @Query(encoded = true, value = "Pincode")
        Pincode: String?,
        @Query(encoded = true, value = "VehicleNumber")
        VehicleNumber: String?,
        @Query(encoded = true, value = "macAddress")
        macAddress: String?
    ): Observable<RegistrationResponsePojo>

/*
//    @GET("/3/movie/top_rated")
//    fun fetchMovieList(@Query(encoded=true,value = "api_key") api_key:String?,
//                    @Query(encoded=true,value = "language") language:String?,
//                    @Query(encoded=true,value = "page") page:Int?): Observable<DataResponse>

    @GET("Login/GetDealers?")
    fun getDealerDetails(
        @Query(encoded = true, value = "DEALER_ID") dealer_id: String?
    ): Observable<DealerDetailResponse>

    @GET("Login/ValidateLogin?")
    fun validateUser(
        @Query(encoded = true, value = "DEALER_ID") dealer_id: String?,
        @Query(encoded = true, value = "BRANCH_ID")
        branch_id: String?,
        @Query(encoded = true, value = "LOGIN_ID")
        login_id: String?,
        @Query(encoded = true, value = "PASSWORD")
        password: String?,
        @Query(encoded = true, value = "ROLE_ID")
        role_id: String?,
        @Query(encoded = true, value = "IS_ONLINE")
        is_online: String?
    ): Observable<LoginDetailResponse>

    @GET("Masters/GetMasterDropDown")
    fun getMastersData(
        @Query(encoded = true, value = "DEALER_ID") dealer_id: String?,
        @Query(encoded = true, value = "BRANCH_ID") branch_id: String?,
        @Query(encoded = true, value = "COUNTRY_CODE") country_code: String?,
        @Query(encoded = true, value = "IS_ONLINE") is_online: String?
    ): Observable<MasterDataRes>

    @POST("5SApp/post5SData")
    fun postData(
        @Body postData: PostData?
    ): Observable<AuditDetailsResponse>

    @GET("5SApp/getLocation")
    fun getLocationDetails(
    ): Observable<LocationDetailsResponse>


    @GET("5SApp/getDetails?")
    fun getDataList(
        @Query(encoded = true, value = "_dealer_id") _dealer_id: String?,
        @Query(encoded = true, value = "_branch_id") _branch_id: String?,
        @Query(encoded = true, value = "_location_id") _location_id: String?,
        @Query(encoded = true, value = "IS_ONLINE") IS_ONLINE: String?
    ): Observable<DealerListResponse>*/
}