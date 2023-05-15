package com.example.orderapplication.order_feature.domain.use_case

import com.example.orderapplication.core.domain.SelectAndSortableByName

class SortListByNameUseCase {

    operator fun <T>invoke(list : List<T>): List<T> where T : SelectAndSortableByName{
        return list.sortedBy {
            it.name
        }
    }
}