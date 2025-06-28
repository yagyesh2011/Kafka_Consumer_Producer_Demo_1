package com.yagyesh.movie.movieproducer.contants;

public class TMDBEndpoints {
    public static final String BASE_URL = "https://api.themoviedb.org/3";
    public static final String DISCOVER_MOVIE = BASE_URL + "/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&api_key={apiKey}";
    public static final String MOVIE_DETAILS = BASE_URL + "/movie/{movieId}?api_key={apiKey}&language=en-US";
    public static final String MOVIE_CREDITS = BASE_URL + "/movie/{movieId}/credits?api_key={apiKey}&language=en-US";
    public static final String MOVIE_IMAGES = BASE_URL + "/movie/{movieId}/images?api_key={apiKey}&language=en-US&include_image_language=en,null";
    public static final String MOVIE_VIDEOS = BASE_URL + "/movie/{movieId}/videos?api_key={apiKey}&language=en-US";
    public static final String MOVIE_KEYWORDS = BASE_URL + "/movie/{movieId}/keywords?api_key={apiKey}&language=en-US";
    public static final String MOVIE_RECOMMENDATIONS = BASE_URL + "/movie/{movieId}/recommendations?api_key={apiKey}&language=en-US&page=1";
    public static final String MOVIE_SIMILAR = BASE_URL + "/movie/{movieId}/similar?api_key={apiKey}&language=en-US&page=1";
    public static final String MOVIE_RATINGS = BASE_URL + "/movie/{movieId}/rating?api_key={apiKey}&language=en-US";
    public static final String MOVIE_GENRES = BASE_URL + "/genre/movie/list?api_key={apiKey}&language=en-US";
    public static final String SEARCH_MOVIE = BASE_URL + "/search/movie?api_key={apiKey}&language=en-US&query={query}&page=1&include_adult=false";
    public static final String MOVIE_NOW_PLAYING = BASE_URL + "/movie/now_playing?api_key={apiKey}&language=en-US&page=1";
    public static final String MOVIE_POPULAR = BASE_URL + "/movie/popular?api_key={apiKey}&language=en-US&page=1";
    public static final String MOVIE_TOP_RATED = BASE_URL + "/movie/top_rated?api_key={apiKey}&language=en-US&page=1";
    public static final String MOVIE_UPCOMING = BASE_URL + "/movie/upcoming?api_key={apiKey}&language=en-US&page=1";
    public static final String MOVIE_LATEST = BASE_URL + "/movie/latest?api_key={apiKey}&language=en-US";
    public static final String MOVIE_ALTERNATIVE_TITLES = BASE_URL + "/movie/{movieId}/alternative_titles?api_key={apiKey}&language=en-US";
    public static final String MOVIE_RELEASE_DATES = BASE_URL + "/movie/{movieId}/release_dates?api_key={apiKey}&language=en-US";
    public static final String MOVIE_TRANSITION = BASE_URL + "/movie/{movieId}/translations?api_key={apiKey}&language=en-US";
    public static final String MOVIE_LISTS = BASE_URL + "/movie/{movieId}/lists?api_key={apiKey}&language=en-US";
    public static final String MOVIE_ACCOUNT_STATE = BASE_URL + "/movie/{movieId}/account_states?api_key={apiKey}&language=en-US";
    public static final String MOVIE_KEYWORD = BASE_URL + "/keyword/{keywordId}/movies?api_key={apiKey}&language=en-US&page=1";
    public static final String MOVIE_COLLECTION = BASE_URL + "/collection/{collectionId}?api_key={apiKey}&language=en-US";
    public static final String MOVIE_CHANGES = BASE_URL + "/movie/changes?api_key={apiKey}&start_date={startDate}&end_date={endDate}";
    public static final String MOVIE_LATEST_CHANGES = BASE_URL + "/movie/changes?api_key={apiKey}";
    public static final String MOVIE_LIST = BASE_URL + "/list/{listId}?api_key={apiKey}&language=en-US";
    public static final String MOVIE_LIST_DETAILS = BASE_URL + "/list/{listId}/details?api_key={apiKey}&language=en-US";
    public static final String GENRE_LIST = "/genre/movie/list?language=en";
    public static final String GENRE_LIST_WITH_API_KEY = "/genre/movie/list?language=en";
}
