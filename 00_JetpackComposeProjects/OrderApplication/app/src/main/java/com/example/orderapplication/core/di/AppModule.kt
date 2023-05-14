package com.example.orderapplication.core.di

import android.content.Context
import androidx.room.Room
import com.example.orderapplication.core.data.local.OrderDatabase
import com.example.orderapplication.core.data.local.dao.OrderDao
import com.example.orderapplication.core.data.local.dao.ProductDao
import com.example.orderapplication.core.data.local.dao.VendorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOrderDatabase(@ApplicationContext context : Context) : OrderDatabase{
        return Room.databaseBuilder(
            context,
            OrderDatabase::class.java,
            "order_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOrderDao(orderDatabase: OrderDatabase) : OrderDao{
        return orderDatabase.orderDao()
    }

    @Provides
    @Singleton
    fun provideVendorDao(orderDatabase: OrderDatabase) : VendorDao{
        return orderDatabase.vendorDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(orderDatabase: OrderDatabase) : ProductDao{
        return orderDatabase.productDao()
    }
}