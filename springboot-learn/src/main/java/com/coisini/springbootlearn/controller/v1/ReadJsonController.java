package com.coisini.springbootlearn.controller.v1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.coisini.springbootlearn.model.*;
import com.coisini.springbootlearn.repository.*;
import com.coisini.springbootlearn.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 读取json文件
 * @author coisini
 * @date
 * @Version 1.0
 */
@RestController
@RequestMapping("/readJson")
public class ReadJsonController {

    @Autowired
    private SpuRepository spuRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private SpuImgRepository spuImgRepository;

    @Autowired
    private SpuDetailImgRepository spuDetailImgRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private GridCategoryRepository gridCategoryRepository;

    @GetMapping(value = "/readSpu")
    public void readSpu(){
        String jsonStr = JsonUtil.readJsonFile("src/main/resources/json/spu.json");
        JSONObject result = JSONObject.parseObject(jsonStr);

        JSONArray res = (JSONArray) result.get("items");

        for (Object item : res) {
            Spu spu = JSON.parseObject(JSONObject.toJSONString(item), new TypeReference<Spu>(){});
            System.out.println(JSON.toJSONString(spu));
            spuRepository.saveAndFlush(spu);

        }
    }

    @GetMapping(value = "/readWithSpu")
    public void readWithSpu(){
        String jsonStr = JsonUtil.readJsonFile("src/main/resources/json/with_spu.json");
        JSONObject result = JSONObject.parseObject(jsonStr);

        JSONArray spuList = (JSONArray) result.get("spu_list");

        for (Object spuItem : spuList) {
            JSONObject spu = JSONObject.parseObject(JSONObject.toJSONString(spuItem));

            JSONArray skuList = (JSONArray) spu.get("sku_list");
            for (Object skuItem : skuList) {
                Sku sku = JSON.parseObject(JSONObject.toJSONString(skuItem), new TypeReference<Sku>(){});
                System.out.println(JSON.toJSONString(sku));
                skuRepository.saveAndFlush(sku);
            }


            JSONArray spuImgList = (JSONArray) spu.get("spu_img_list");
            for (Object spuImgItem : spuImgList) {
                SpuImg spuImg = JSON.parseObject(JSONObject.toJSONString(spuImgItem), new TypeReference<SpuImg>(){});
                System.out.println(JSON.toJSONString(spuImg));
                spuImgRepository.saveAndFlush(spuImg);
            }

            JSONArray spuDetailImgList = (JSONArray) spu.get("spu_detail_img_list");
            for (Object spuDetailImgItem : spuDetailImgList) {
                SpuDetailImg spuDetailImg = JSON.parseObject(JSONObject.toJSONString(spuDetailImgItem), new TypeReference<SpuDetailImg>(){});
                System.out.println(JSON.toJSONString(spuDetailImg));
                spuDetailImgRepository.saveAndFlush(spuDetailImg);
            }

        }

    }

    /**
     * 分类
     */
    @GetMapping(value = "/readCategory")
    public void readCategory() {
        String jsonStr = JsonUtil.readJsonFile("src/main/resources/json/category.json");
        JSONObject result = JSONObject.parseObject(jsonStr);

        JSONArray roots = (JSONArray) result.get("roots");
        JSONArray subs = (JSONArray) result.get("subs");

        roots.forEach(item -> {
            Category category = JSON.parseObject(JSONObject.toJSONString(item), new TypeReference<Category>(){});
            System.out.println(JSON.toJSONString(category));
            categoryRepository.saveAndFlush(category);
        });

        subs.forEach(item -> {
            Category category = JSON.parseObject(JSONObject.toJSONString(item), new TypeReference<Category>(){});
            System.out.println(JSON.toJSONString(category));
            categoryRepository.saveAndFlush(category);
        });

    }

    /**
     * 主题
     */
    @GetMapping(value = "/readThemes")
    public void readThemes() {
        String jsonStr = JsonUtil.readJsonFile("src/main/resources/json/themes.json");
        JSONArray result = JSONObject.parseArray(jsonStr);

        result.forEach(item -> {
            Theme theme = JSON.parseObject(JSONObject.toJSONString(item), new TypeReference<Theme>(){});
            System.out.println(JSON.toJSONString(theme));
            themeRepository.saveAndFlush(theme);
        });

    }

    /**
     * 主题
     */
    @GetMapping(value = "/readGrid")
    public void readGrid() {
        String jsonStr = JsonUtil.readJsonFile("src/main/resources/json/grid.json");
        JSONArray result = JSONObject.parseArray(jsonStr);

        result.forEach(item -> {
            GridCategory gridCategory = JSON.parseObject(JSONObject.toJSONString(item), new TypeReference<GridCategory>(){});
            System.out.println(JSON.toJSONString(gridCategory));
            gridCategoryRepository.saveAndFlush(gridCategory);
        });

    }

}
