package com.example.musicappui
 import com.example.musicappui.ui.theme.CategoriesResponse
 import retrofit2.Retrofit

 import retrofit2.converter.gson.GsonConverterFactory
 import retrofit2.http.GET


// Define your data classes for JSON parsing



// Retrofit API interface
interface ApiService {
    @GET("uc?id=1NW1adS_r6MefbqMzxFhA5S1_hVvvSLFq")
    suspend fun getCategories(): CategoriesResponse
}

// Create Retrofit instance
val retrofit = Retrofit.Builder()
    .baseUrl("https://drive.google.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// Create service instance using the API interface
val apiService = retrofit.create(ApiService::class.java)



