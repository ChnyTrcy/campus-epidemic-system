package xyz.chnytrcy.campusepidemicsystem.model.entity;

import xyz.chnytrcy.campusepidemicsystem.mapper.CityMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.CountyMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.ProvinceMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StreetMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.chnytrcy.core.common.FileCommon;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class StreetTest {

  @Autowired private FileCommon fileCommon;

  @Autowired private ProvinceMapper provinceMapper;

  @Autowired private CityMapper cityMapper;

  @Autowired private CountyMapper countyMapper;

  @Autowired private StreetMapper streetMapper;

  @Test
  public void initCity(){
    String jsonUrl = "/Users/chnytrcy/Documents/IdeaProject/campus-epidemic-system/地区.json";
    File file = new File(jsonUrl);
    String jsonData = fileCommon.getStr(file);
    JSONArray jsonArray = (JSONArray) JSON.parse(jsonData);
    for (Object o : jsonArray) {
      JSONObject jsonObject = (JSONObject) o;
      String provinceString = (String) jsonObject.get("name");
      String provinceCode = String.valueOf(jsonObject.get("code"));
      Province province = new Province(provinceCode.substring(0,2),provinceString);
      province.setCreateUser("supAdmin");
      province.setUpdateUser("supAdmin");
      provinceMapper.insert(province);

      JSONArray children = (JSONArray) jsonObject.get("children");
      for (Object child : children) {
        JSONObject jsonObject1 = (JSONObject) child;
        String cityCode = String.valueOf(jsonObject1.get("code"));
        String cityName = (String) jsonObject1.get("name");
        City city = new City(cityName,
            cityCode.substring(0,4),
            cityCode.substring(0,2),
            provinceString + cityName);
        city.setCreateUser("supAdmin");
        city.setUpdateUser("supAdmin");
        cityMapper.insert(city);

        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("children");
        for (Object o1 : jsonArray1) {
          JSONObject jsonObject2 = (JSONObject) o1;
          String countyName = (String) jsonObject2.get("name");
          String countyCode = String.valueOf(jsonObject2.get("code")) ;
          County county = new County(
              countyCode.substring(0,6),
              countyName,
              countyCode.substring(0,4),
              countyCode.substring(0,2),
              provinceString + cityName + countyName
          );
          county.setCreateUser("supAdmin");
          county.setUpdateUser("supAdmin");
          countyMapper.insert(county);
        }
      }
    }

  }

  @Test
  public void initStreet(){
    Map<String, String> collect = countyMapper.selectList(null).stream()
        .collect(Collectors.toMap(County::getCode, County::getFullName));

    String jsonUrl = "/Users/chnytrcy/Documents/IdeaProject/campus-epidemic-system/streets.json";
    File file = new File(jsonUrl);
    String jsonData = fileCommon.getStr(file);
    JSONArray jsonArray = (JSONArray) JSON.parse(jsonData);
    for (Object o : jsonArray) {
      JSONObject jsonObject = (JSONObject) o;
      String streetCode = (String) jsonObject.get("code");
      String streetName = (String) jsonObject.get("name");
      String provinceCode = (String) jsonObject.get("provinceCode");
      String cityCode = (String) jsonObject.get("cityCode");
      String countyCode = (String) jsonObject.get("areaCode");
      String fullName = collect.get(countyCode) + streetName;
      Street street = new Street(
          streetCode,
          streetName,
          provinceCode,
          cityCode,
          countyCode,
          fullName,
          0
      );
      street.setCreateUser("supAdmin");
      street.setUpdateUser("supAdmin");
      streetMapper.insert(street);
    }
  }
}