package com.example.android.marsphotos.network

import com.squareup.moshi.Json

//todo 11 create data class to parse and store the data returned from the web
//Moshi parses this JSON data and converts it into Kotlin objects. To do this, Moshi needs to have a Kotlin data class to store the parsed results, so in this step you will create the data class, MarsPhoto.
data class MarsPhoto (
//Notice that each of the variables in the MarsPhoto class corresponds to a key name in the JSON object. To match the types in our specific JSON response, you use String objects for all the values.
//When Moshi parses the JSON, it matches the keys by name and fills the data objects with appropriate values.
    val id:String,

    //@Json Annotation
    //Sometimes the key names in a JSON response can make confusing Kotlin properties, or may not match recommended coding style—for example, in the JSON file the img_src key uses an underscore, whereas Kotlin convention for properties use upper and lowercase letters ("camel case").
    @Json(name="img_src")val imgSrcUrl:String
)