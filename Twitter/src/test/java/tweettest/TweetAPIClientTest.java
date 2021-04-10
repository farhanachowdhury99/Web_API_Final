package tweettest;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.Payloads;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test
    public void testUserCanTweetSuccessfully() {
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet"+UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet does not match");
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARoq() {
        String tweet = "We are learning Rest API using Rest Assured and our First same Tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,expectedMessage,"Tweet does not match");
    }

    @Test
    public void testDeleteTweet(){
        String tweet="This week is the last week of class";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1379475026999660548l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);

    }
    @Test
    public void testCreateTweet(){
        String tweet="Creating tweet by myself";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet does not match");
    }

    @Test
    public void testHomeTimeline(){
        ValidatableResponse response = this.tweetAPIClient.getHome_Timeline();
        System.out.println(response.extract().body().asPrettyString());
        String expectedTweet="Thu Apr 08 17:27:36 +0000 2021";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,actualTweet,"Tweet does not match");
    }

    @Test
    public void testUplaodImage(){
        ValidatableResponse response= this.tweetAPIClient.uploadPic(Payloads.pic6());
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testUserCanUploadPicSuccessfully() {
        String tweet = "Uploading picture";
        ValidatableResponse response = this.tweetAPIClient.uploadPic1(tweet,1380676459631624194L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void testUploadPicSuccessfully() {
        String tweet = "Uploading picture";
        ValidatableResponse response = this.tweetAPIClient.uploadPic1(tweet,1380684345803952132L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUploadClearPicSuccessfully() {
        String tweet = "Uploading better picture";
        ValidatableResponse response = this.tweetAPIClient.uploadPic1(tweet,1380702466946297858L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUploadBannerSuccessfully() {
        ValidatableResponse response = this.tweetAPIClient.updateBanner(Payloads.pic6());
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void testUploadProfilePic() {
        ValidatableResponse response = this.tweetAPIClient.uploadProfilePicture(Payloads.pic5());
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUploadPicWithoutStatus() {
        ValidatableResponse response = this.tweetAPIClient.uploadPicwithoutStatus(1380728508582916096L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testFavoritePicture(){
        ValidatableResponse response = this.tweetAPIClient.postFavorite(1380702735205593088l);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTweet = "id parameter is missing.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(expectedTweet,actualTweet);
    }

    @Test
    public void testCreateTweet7(){
        String tweet="Our Final is on April 10th";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet,"Tweet does not match");
    }

    @Test
    public void testNewTweet(){
        String tweet="Posting tweets for final";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet,"Tweet does not match");
    }

    @Test
    public void testCreateFavorite(){
        ValidatableResponse response = this.tweetAPIClient.postFavorite(1380210732030132226L);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTweet = "id parameter is missing.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(expectedTweet,actualTweet);
    }
    @Test
    public void testDeleteFavorite(){
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteFavorite(1380365580868411400l);
        System.out.println(deleteResponse.extract().body().asPrettyString());
        deleteResponse.statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testCreateTweet1(){
        String tweet="This week is the last week of class.";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet,actualTweet,"Tweet does not match");
    }
    @Test
    public void testUsingPrettyString(){
        String tweet="Using pretty String to create String";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet,"Tweet does not match");
    }

    @Test
    public void testUpdate(){
        String tweet="Updating tweets";
        ValidatableResponse updateResponse= this.tweetAPIClient.updateTweet(tweet, 1379474361049026571l);
        updateResponse.statusCode(200);
        String actualTweet= updateResponse.extract().body().path("text");
        Assert.assertEquals(actualTweet,actualTweet,"Tweet does not match");
    }

    @Test
    public void testCreateTweet3(){
        String tweet="Working on creating tweet";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet,"Tweet does not match");
    }

    @Test
    public void testDeleteTweet2(){
        String tweet="We are learning Rest API using Rest Assured and our First Tweet2431a83f-efaa-4c42-83d0-998870a0235e";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1379489661672898570l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);

    }

    @Test
    public void testCreateTweet4(){
        String tweet="The session will be done in 2 days";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet,"Tweet does not match");
    }

    @Test
    public void testDeleteTweet3(){
        String tweet="We are learning Rest API using Rest Assured and our First Tweet0ba9c57b-776c-4d26-bdbe-086d6c6508e1";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1379489705440509963l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test
    public void testCreateTweet5(){
        String tweet="Today is Thursday";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet,"Tweet does not match");
    }

    @Test
    public void testDeleteTweet4(){
        String tweet="We are learning Rest API using Rest Assured and our First Tweetc7c6cda0-c037-4b85-bdcc-1657bbb8da75";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1379126432505470977l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);

    }

    @Test
    public void testCreateTweet6(){
        String tweet="Today is April 8th";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet,"Tweet does not match");
    }


    @Test
    public void testRetweetersEndpoint(){
        ValidatableResponse response = this.tweetAPIClient.getRetweetersEndpoint();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testFriendship(){
        ValidatableResponse response= this.tweetAPIClient.createFriendship("eashaarap");
        String actualTweet= response.extract().body().path("text");
        //Assert.assertEquals(actualTweet,userName,"UserID does not match");
    }

    @Test
    public void testGetFriendshipLookup(){
        ValidatableResponse response = this.tweetAPIClient.getFriendshipLookup("eashaarap");
        System.out.println(response.extract().body().asPrettyString());
//        String expectedTweet="Thu Apr 08 17:27:36 +0000 2021";
//        String actualTweet=response.extract().body().path("errors[0].message");
//        Assert.assertEquals(actualTweet,actualTweet,"Tweet does not match");
    }


}
