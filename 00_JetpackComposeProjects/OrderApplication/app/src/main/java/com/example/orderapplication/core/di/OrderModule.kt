package com.example.orderapplication.core.di

import android.content.Context
import androidx.room.Room
import com.example.orderapplication.core.data.local.OrderDatabase
import com.example.orderapplication.core.data.local.dao.DelivererDao
import com.example.orderapplication.core.data.local.dao.OrderDao
import com.example.orderapplication.core.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {

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
    fun provideDelivererDao(orderDatabase: OrderDatabase) : DelivererDao{
        return orderDatabase.delivererDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(orderDatabase: OrderDatabase) : ProductDao{
        return orderDatabase.productDao()
    }
}