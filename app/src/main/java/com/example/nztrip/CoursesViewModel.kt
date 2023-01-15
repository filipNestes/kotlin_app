package com.example.nztrip
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class CoursesViewModel: ViewModel() {

    val currenciesLiveData = MutableLiveData<Response?>()

    var baseUrl = "https://api.currencyapi.com/"

    fun getCurrencyData() {

        val httpClient = OkHttpClient.Builder().addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .build()

        val myApi = retrofitBuilder.create(RatesApi::class.java)
        val call = myApi.getExchangeRates()
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<com.example.nztrip.Response>) {
                if (response.isSuccessful) {
                    val user = response.body().logE("response")
                    currenciesLiveData.postValue(user)
                    // do something with the user
                } else {
                    response.errorBody()?.toString().logE("fail")
                    response.message().toString().logE("fail 2")
                    currenciesLiveData.postValue(null)
                    // handle error
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                // handle failure
                t.printStackTrace()
            }
        })
    }
}

interface RatesApi {
    @GET("v3/latest?apikey=T6J307MaLlAPmrTea5QwDrqlUV0ooCkOQIDsn1sV&currencies=EUR%2CNZD&base_currency=NZD")
    fun getExchangeRates(): Call<Response>
}

fun <A> A.logE(tag: String = "") = apply {
    if(BuildConfig.DEBUG) {
        Log.e(tag, toString())
    }
}

class Meta {
    @SerializedName("last_updated_at")
    val lastUpdatedAt: String? = null
}

class Currency {
    val code: String? = null
    val value: Double? = null
}

class Data {
    @SerializedName("EUR")
    val eur: Currency? = null
    @SerializedName("NZD")
    val nzd: Currency? = null
}

class Response {
    val meta: Meta? = null
    val data: Data? = null
}

fun getCurrency(context: Context, code: String): Double? {
    val storedValue = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE).getFloat(code, -1F)
    return if(storedValue != -1F) storedValue.toDouble() else null
}

fun setCurrency(context: Context, code: String, value: Double) =
    context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE).edit().putFloat(code, value.toFloat()).commit()