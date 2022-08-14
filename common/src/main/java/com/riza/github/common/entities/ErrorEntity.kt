package com.riza.github.common.entities

data class ErrorEntity(val message: String) {
    companion object {
        fun empty() = ErrorEntity("")
    }
}
