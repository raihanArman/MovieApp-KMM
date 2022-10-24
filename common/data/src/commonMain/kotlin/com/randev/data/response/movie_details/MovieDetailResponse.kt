package com.randev.data.response.movie_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    @SerialName("budget")
    val budget: Int?,
    @SerialName("genres")
    val genres: List<Genre?>?,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("images")
    val images: Images?,
    @SerialName("imdb_id")
    val imdbId: String?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany?>?,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry?>?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("revenue")
    val revenue: Int?,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>?,
    @SerialName("status")
    val status: String?,
    @SerialName("tagline")
    val tagline: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("videos")
    val videos: Videos?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
) {
    @Serializable
    data class BelongsToCollection(
        @SerialName("backdrop_path")
        val backdropPath: String?,
        @SerialName("id")
        val id: Int?,
        @SerialName("name")
        val name: String?,
        @SerialName("poster_path")
        val posterPath: String?
    )

    @Serializable
    data class Genre(
        @SerialName("id")
        val id: Int?,
        @SerialName("name")
        val name: String?
    )

    @Serializable
    data class Images(
        @SerialName("backdrops")
        val backdrops: List<Backdrop?>?,
        @SerialName("logos")
        val logos: List<Logo?>?,
        @SerialName("posters")
        val posters: List<Poster?>?
    ) {
        @Serializable
        data class Backdrop(
            @SerialName("aspect_ratio")
            val aspectRatio: Double?,
            @SerialName("file_path")
            val filePath: String?,
            @SerialName("height")
            val height: Int?,
            @SerialName("iso_639_1")
            val iso6391: String?,
            @SerialName("vote_average")
            val voteAverage: Double?,
            @SerialName("vote_count")
            val voteCount: Int?,
            @SerialName("width")
            val width: Int?
        )

        @Serializable
        data class Logo(
            @SerialName("aspect_ratio")
            val aspectRatio: Double?,
            @SerialName("file_path")
            val filePath: String?,
            @SerialName("height")
            val height: Int?,
            @SerialName("iso_639_1")
            val iso6391: String?,
            @SerialName("vote_average")
            val voteAverage: Double?,
            @SerialName("vote_count")
            val voteCount: Int?,
            @SerialName("width")
            val width: Int?
        )

        @Serializable
        data class Poster(
            @SerialName("aspect_ratio")
            val aspectRatio: Double?,
            @SerialName("file_path")
            val filePath: String?,
            @SerialName("height")
            val height: Int?,
            @SerialName("iso_639_1")
            val iso6391: String?,
            @SerialName("vote_average")
            val voteAverage: Double?,
            @SerialName("vote_count")
            val voteCount: Int?,
            @SerialName("width")
            val width: Int?
        )
    }

    @Serializable
    data class ProductionCompany(
        @SerialName("id")
        val id: Int?,
        @SerialName("logo_path")
        val logoPath: String?,
        @SerialName("name")
        val name: String?,
        @SerialName("origin_country")
        val originCountry: String?
    )

    @Serializable
    data class ProductionCountry(
        @SerialName("iso_3166_1")
        val iso31661: String?,
        @SerialName("name")
        val name: String?
    )

    @Serializable
    data class SpokenLanguage(
        @SerialName("english_name")
        val englishName: String?,
        @SerialName("iso_639_1")
        val iso6391: String?,
        @SerialName("name")
        val name: String?
    )

    @Serializable
    data class Videos(
        @SerialName("results")
        val results: List<Result?>?
    ) {
        @Serializable
        data class Result(
            @SerialName("id")
            val id: String?,
            @SerialName("iso_3166_1")
            val iso31661: String?,
            @SerialName("iso_639_1")
            val iso6391: String?,
            @SerialName("key")
            val key: String?,
            @SerialName("name")
            val name: String?,
            @SerialName("official")
            val official: Boolean?,
            @SerialName("published_at")
            val publishedAt: String?,
            @SerialName("site")
            val site: String?,
            @SerialName("size")
            val size: Int?,
            @SerialName("type")
            val type: String?
        )
    }
}