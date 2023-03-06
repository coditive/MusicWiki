package com.example.musicwiki.data.remote

import com.example.musicwiki.data.remote.model.artist.ArtistInfo
import com.example.musicwiki.data.remote.response.TopAlbumOfArtist
import com.example.musicwiki.data.remote.response.TopAlbums
import com.example.musicwiki.data.remote.response.TopArtists
import com.example.musicwiki.data.remote.response.TopTracks
import com.example.musicwiki.data.remote.response.TopTracksOfArtist
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("2.0/?method=tag.gettopalbums")
    fun getArtistListFromTag(
        @Query("tag") tag: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ): TopArtists

    @GET("2.0/?method=tag.gettopalbums")
    fun getAlbumListFromTag(
        @Query("tag") tag: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ): TopAlbums

    @GET("2.0/?method=tag.gettopalbums")
    fun getTrackListFromTag(
        @Query("tag") tag: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ): TopTracks

    @GET("2.0/?method=artist.getinfo")
    fun getArtistInfoByName(
        @Query("artist") artistName: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ): ArtistInfo

    @GET("2.0/?method=artist.gettopalbums")
    fun getTopAlbumsOfArtistByName(
        @Query("artist") artistName: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ): TopAlbumOfArtist

    @GET("2.0/?method=artist.gettoptracks")
    fun getTopTracksOfArtistByName(
        @Query("artist") artistName: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ): TopTracksOfArtist

}