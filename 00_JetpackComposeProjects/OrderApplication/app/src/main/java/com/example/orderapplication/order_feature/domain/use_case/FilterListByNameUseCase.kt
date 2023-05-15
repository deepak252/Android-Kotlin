package com.example.orderapplication.order_feature.domain.use_case

import com.example.orderapplication.core.domain.SelectAndSortableByName

class FilterListByNameUseCase {
     operator fun <T>invoke(
         list : List<T>, name : String
     ) : List<T> where T : SelectAndSortableByName{
         return list.filter { item->
             item.name.lowercase().contains(name.lowercase())
         }
     }
}