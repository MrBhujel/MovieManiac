import com.example.moviemaniac.domain.model.Genre

data class MovieDetailScreenResponseDto(
    val id: Int,
    val imdbId: String,
    val originalTitle: String,
    val title: String,
    val backdropPath: String,
    val posterPath: String,
    val genres: List<Genre>,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val voteAverage: Double,
    val voteCount: Int
)
