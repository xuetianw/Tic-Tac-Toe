package io.futurestud.retrofit1.api.proxy;

import java.util.List;

import io.futurestud.retrofit1.api.model.Course;
import io.futurestud.retrofit1.api.model.Game;
import io.futurestud.retrofit1.api.model.Move;
import io.futurestud.retrofit1.api.model.Player;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WGServerProxy {
    @GET("games")
    Call<List<Game>> getgames();

    @GET("about")
    Call<String> getabout();

    @POST("games")
    Call<Game> postgames(@Body Game game);

    @POST("games/{id}/moves")
    Call<Move> makeMove(
            @Path("id") Long id,
            @Body Move move);

    @GET("games")
    Call<List<Game>> getAllGames(
            @Body List<Game> game);

    @GET("games/{id}")
    Call<Game> getGame(@Path("id") Long id);

    @GET("players")
    Call<Player> getAllPlayer();

    @HTTP(method = "GET", path = "/player", hasBody = true)
    Call<Player> getlPlayer(@Body Player player);

    @POST("player")
    Call<Player> make_player(@Body Player player);

    @DELETE("player")
    Call<Player> delete_player(@Body Player player);

    @PUT("player")
    Call <Player> updatePlayer(@Body Player player);

    @PUT("login")
    Call <Player> login(@Body Player player);

    @POST("/signup")
    Call<Player> sign_up(@Body Player player);

    @GET("/courses")
    Call<List<Course>> get_courses();




}