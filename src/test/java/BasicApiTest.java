import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicApiTest{
    @Test
    public void getWaltersBirthday() {
        //res = util.RestAssuredUtil.getResponse("/api/characters/1");
        List<Map<String,Object>> responseBody =
                RestAssured
                        .given()
                        .baseUri("http://www.breakingbadapi.com/api")
                        .basePath("/characters/1")
                        .when()
                        .get()
                        .then()
                        .extract()
                        .body()
                        // Extract response as List<Map<String,Object>>
                        // Since the response in a List of Map format.
                        .as(new TypeRef<>() {});

        for(Map<String,Object> booking : responseBody)
        {
            System.out.println("Walter's Birthday is: " + booking.get("birthday"));
        }
        //testUtil.checkStatusIs200(res);
        //List<Map<String,Object>> responseBody = res.as(new TypeRef<>() {});
        //System.out.println("Total bookings : "+ responseBody.size());
        //System.out.println(res);
        //jp = util.RestAssuredUtil.getJsonPath(res);
        //String birthday = jp.get("birthday");
        //System.out.println(birthday);
    }
    @Test
    public void setActorsInfo(){

        actorsInfoPojo actor = new actorsInfoPojo();
        int position = 0;

        List<Map<String,Object>> responseBody =
                RestAssured
                        .given()
                        .baseUri("http://www.breakingbadapi.com/api")
                        .basePath("/characters")
                        .when()
                        .get()
                        .then()
                        .extract()
                        .body()
                        // Extract response as List<Map<String,Object>>
                        // Since the response in a List of Map format.
                        .as(new TypeRef<>() {});

        for(Map<String,Object> booking : responseBody)
        {
            actor.setCharacter(booking.get("name").toString());
            actor.setActor(booking.get("portrayed").toString());
        }


        for(Map<String,Object> booking : responseBody)
        {
            System.out.println("Character name: " + actor.getCharacter(position));
            System.out.println("Portrayed: " + actor.getActor(position));
            System.out.println("--------------------------------");

            position++;
        }
    }

}