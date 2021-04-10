package tweet;

import base.RestAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT="/statuses/home_timeline.json";
    private final String UPDATE_TWEET_ENDPOINT="/statuses/update.json";
    private final String HOME_TIMELINE="/statuses/home_timeline.json";
    private final String GET_RETWEETERS_ENDPOINT="/statuses/retweeters/ids.json";
    private final String CREATE_FRIENDSHIP_ENDPOINT="/friendships/create.json";
    private final String GET_FRIENDSHIP_LOOKUP_ENDPOINT="/friendships/lookup.json";
    private final String POST_FAVORITE_ENDPOINT="/favorites/create.json";
    private final String DELETE_FAVORITE_ENDPOINT="/favorites/destroy.json";
    private final String UPDATE_PROFILE_PICTURE_ENDPOINT="/favorites/destroy.json";
    private final String UPLOAD_MEDIA_ENDPOINT="/media/upload.json";
    private final String UPDATE_PROFILE_BANNER_ENDPOINT="/account/update_profile_banner.json";
    private final String POST_UPDATE_PP_ENDPOINT="/account/update_profile_image.json";

    // GET all Tweet Information
    public ValidatableResponse getUserTimeTweet(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT).then().statusCode(200);
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from user twitter
    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUrl+this.DELETE_TWEET_ENDPOINT).then().statusCode(200);
    }

    public ValidatableResponse updateTweet(String tweet, Long tweetID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                //.queryParam("id",tweetID)
                .param("status",tweet,"id",tweetID)
                .when().put(this.baseUrl+this.UPDATE_TWEET_ENDPOINT)
                .then();
    }


    // Response Time check
    public ValidatableResponse responseTime(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then();

    }

    // Header Value
    public void headerValue(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then().extract().headers());

    }

    public  void checkProperty(){
        Response response= given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator= response.jsonPath();
        String createdAt=pathEvaluator.get("id");
        System.out.println(createdAt);
    }

    public ValidatableResponse getHome_Timeline(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.HOME_TIMELINE).then().statusCode(200);
    }
    public ValidatableResponse getRetweetersEndpoint(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_RETWEETERS_ENDPOINT).then().statusCode(200);
    }
    public ValidatableResponse createFriendship(String userName){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.CREATE_FRIENDSHIP_ENDPOINT).then().statusCode(200);
    }
    public ValidatableResponse getFriendshipLookup(String userName){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_FRIENDSHIP_LOOKUP_ENDPOINT).then().statusCode(200);
    }
    public ValidatableResponse postFavorite(long id){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",id)
                .when().post(this.baseUrl+this.POST_FAVORITE_ENDPOINT)
                .then();
    }
    public ValidatableResponse deleteFavorite(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUrl+this.DELETE_FAVORITE_ENDPOINT).then().statusCode(200);
    }
//    public ValidatableResponse updateProfilePicture(String userName){
//        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
//                .param("status",userName)
//                .when().post(this.baseUrl+this.UPDATE_PROFILE_PICTURE_ENDPOINT
//               // .then();
//   // }

    public ValidatableResponse uploadPic(String image){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("media",image)
                .when().post(this.uploadImageBase+this.UPLOAD_MEDIA_ENDPOINT)
                .then();
    }

    public ValidatableResponse uploadPic1(String tweet, Long mediaID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .param("media_ids",mediaID)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse uploadPic2(String tweet, Long mediaID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .param("media_ids",mediaID)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse uploadPic3(String tweet, Long mediaID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .param("media_ids",mediaID)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse uploadPic4(String tweet, Long mediaID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .param("media_ids",mediaID)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse updateBanner(String bannerID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("banner",bannerID)
                .when().post(this.baseUrl+this.UPDATE_PROFILE_BANNER_ENDPOINT)
                .then();
    }
    public ValidatableResponse uploadProfilePicture(String profileImage){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("image",profileImage)
                .when().post(this.baseUrl+this.POST_UPDATE_PP_ENDPOINT)
                .then();
    }
    public ValidatableResponse uploadPicwithoutStatus( Long mediaID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("media_ids",mediaID)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    }
















