package com.example.faunaonboarding.util

import com.apollographql.apollo.api.ScalarType

enum class CustomType : ScalarType {
    ID {
        override fun typeName(): String = "ID"

        override fun className(): String = "kotlin.String"
    }
}