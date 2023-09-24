package gamer.botixone.footymatch.ui.theme.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton
import gamer.botixone.footymatch.R
import gamer.botixone.footymatch.ui.theme.data.endpoints.Endpoints
import gamer.botixone.footymatch.ui.theme.data.repository.datasource.remote.UserRemoteDataSource
import gamer.botixone.footymatch.ui.theme.data.repository.interfaces.UserServiceRemote
import gamer.botixone.footymatch.ui.theme.domain.user.AccountUseCase
import gamer.botixone.footymatch.ui.theme.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providerSharedPreferences(@ApplicationContext appContext: Context) : SharedPreferences =
        appContext.applicationContext.getSharedPreferences(appContext.getString(R.string.shared_preferences), Context.MODE_PRIVATE)

    @Provides
    fun provideTokenManager(sharedPreferences: SharedPreferences):
            TokenManager = TokenManager(sharedPreferences)

    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager):
            AuthInterceptor = AuthInterceptor(tokenManager)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

    @Singleton
    @Provides
    @Named("api")
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl(Endpoints.domainApi)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()



    //user
    @Provides
    fun provideUserService(@Named("api") retrofit: Retrofit): UserServiceRemote = retrofit.create(UserServiceRemote::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userServiceRemote: UserServiceRemote) = UserRemoteDataSource(userServiceRemote)

    @Singleton
    @Provides
    fun provideAccountUseCase(userDataSourceRemote: UserRemoteDataSource) = AccountUseCase(userDataSourceRemote)
}