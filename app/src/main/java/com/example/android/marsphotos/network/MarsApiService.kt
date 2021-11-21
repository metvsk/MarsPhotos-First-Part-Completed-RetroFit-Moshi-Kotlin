package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//todo 12 remove imports from removed dependency of retro we updated the moshi dependency which take care of retrofit and json management
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
    //TODO 01 : add a Retrofit builder to build and create a Retrofit object. AND
    //Retrofit needs the base URI for the web service, and a converter factory to build a web services API. The converter tells Retrofit what to do with the data it gets back from the web service. In this case, you want Retrofit to fetch a JSON response from the web service, and return it as a String. Retrofit has a ScalarsConverter that supports strings and other primitive types, so you call addConverterFactory() on the builder with an instance of ScalarsConverterFactory.

//todo 13 insert moshi Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes:
private  val moshi=Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

    private val retrofit = Retrofit.Builder()

        //todo 14 remove scalar calls since we removed the dependency and is using moshi
        //.addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
//TODO 02 : Add an interface for actions
    interface MarsApiService {
    //TODO 03 When the getPhotos() method is invoked, Retrofit appends the endpoint photos to the base URL (which you defined in the Retrofit builder) used to start the request. Add a return type of the function to String.
    @GET("photos")
//    suspend fun getPhotos(): String//before moshi
    suspend fun getPhotos():List<MarsPhoto>//TODO 4: add desired return type

    //todo 15 change the return type to data class
    //TODO 7: MAKE THIS SUSPEND SO THAT WE CAN ACCESS IT VIA A COROUTINE INSIDE THE VIEW MODEL -> WHICH WE WILL CREATE USING VIEW MODEL COROUTINE SCOPE PROVIDED BY THE VIEW MODEL SYSTEM ITSELF
}
object MarsApi {
    //TODO 5 :  initialize the Retrofit service. This is the public singleton object that can be accessed from the rest of the app.
// The call to create() function on a Retrofit object is expensive and the app needs only one instance of Retrofit API service. So, you expose the service to the rest of the app using object declaration.
//    Object declarations
//    In kotlin, object declarations are used to declare singleton objects. Singleton pattern ensures that one, and only one, instance of an object is created, has one global point of access to that object. Object declaration's initialization is thread-safe and done at first access.
//    Kotlin makes it easy to declare singletons. Following is an example of an object declaration and its access. Object declaration always has a name following the object keyword.
    //TODO 6: Inside the MarsApi object declaration, add a lazily initialized retrofit object property named retrofitService of the type MarsApiService. You make this lazy initialization, to make sure it is initialized at its first usage. You will fix the error in the next steps.
    val retrofitService:MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    } //The Retrofit setup is done! Each time your app calls MarsApi.retrofitService, the caller will access the same a singleton Retrofit object that implements MarsApiService which is created on the first access. In the next task, you will use the Retrofit object you have implemented.
}
