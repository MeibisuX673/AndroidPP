package com.example.showphoto.model

data class ListPhoto(
    val countOfPages: Int,
    val `data`: List<Data>,
    val itemsPerPage: Int,
    val totalItems: Int
)