package com.example.orderapplication.vendor_feature.di

import com.example.orderapplication.core.data.local.dao.ProductDao
import com.example.orderapplication.core.data.local.dao.VendorDao
import com.example.orderapplication.vendor_feature.data.repository.VendorRepositoryImpl
import com.example.orderapplication.vendor_feature.domain.repository.VendorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VendorFeatureModule {

    @Provides
    @Singleton
    fun provideVendorRepository(
        vendorDao: VendorDao,
        productDao: ProductDao
    ) : VendorRepository{
        return VendorRepositoryImpl(vendorDao, productDao )
    }
}