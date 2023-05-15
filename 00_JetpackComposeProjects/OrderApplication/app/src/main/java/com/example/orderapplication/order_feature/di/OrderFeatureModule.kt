package com.example.orderapplication.order_feature.di

import com.example.orderapplication.core.data.local.dao.OrderDao
import com.example.orderapplication.core.data.local.dao.ProductDao
import com.example.orderapplication.core.data.local.dao.VendorDao
import com.example.orderapplication.order_feature.data.repository.OrderRepositoryImpl
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import com.example.orderapplication.order_feature.domain.use_case.FilterListByNameUseCase
import com.example.orderapplication.order_feature.domain.use_case.SortListByNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderFeatureModule {
    @Provides
    @Singleton
    fun providesOrderRepository(
        orderDao: OrderDao,
        vendorDao: VendorDao,
        productDao: ProductDao
    ) : OrderRepository{
        return OrderRepositoryImpl(
            orderDao,vendorDao,productDao
        )
    }

    @Provides
    @Singleton
    fun provideFilterListByNameUseCase() : FilterListByNameUseCase{
        return FilterListByNameUseCase()
    }

    @Provides
    @Singleton
    fun provideSortListByNameUseCase() : SortListByNameUseCase{
        return SortListByNameUseCase()
    }
}