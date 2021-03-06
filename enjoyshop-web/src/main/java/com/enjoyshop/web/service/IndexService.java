package com.enjoyshop.web.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.enjoyshop.common.service.ApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service
public class IndexService {

	@Autowired
	private ApiService apiService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Value("${ENJOYSHOP_MANAGE_URL}")
	private String ENJOYSHOP_MANAGE_URL;

	@Value("${INDEX_AD1_URL}")
	private String INDEX_AD1_URL;

	@Value("${INDEX_AD2_URL}")
	private String INDEX_AD2_URL;

	public String queryIndexAD1() {
		try {
			String url = ENJOYSHOP_MANAGE_URL + INDEX_AD1_URL;
			String jsonData = this.apiService.doGet(url);
			if (StringUtils.isEmpty(jsonData)) {
				return null;
			}
			// 解析json，生成相应的json数据
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			ArrayNode rows = (ArrayNode) jsonNode.get("rows");
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (JsonNode row : rows) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("srcB", row.get("pic").asText());
				map.put("height", 240);
				map.put("alt", row.get("title").asText());
				map.put("width", 670);
				map.put("src", row.get("pic").asText());
				map.put("widthB", 550);
				map.put("href", row.get("url").asText());
				map.put("heightB", 240);
				result.add(map);
			}
			return MAPPER.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public String queryIndexAD2() {
		try {
			String url = ENJOYSHOP_MANAGE_URL + INDEX_AD2_URL;
			String jsonData = this.apiService.doGet(url);
			if (StringUtils.isEmpty(jsonData)) {
				return null;
			}
			// 解析json，生成前端所需要的json数据
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			ArrayNode rows = (ArrayNode) jsonNode.get("rows");

			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			for (JsonNode row : rows) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("width", 310);
				map.put("height", 70);
				map.put("src", row.get("pic").asText());
				map.put("href", row.get("url").asText());
				map.put("alt", row.get("title").asText());
				map.put("widthB", 210);
				map.put("heightB", 70);
				map.put("srcB", row.get("pic").asText());
				result.add(map);
			}
			return MAPPER.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
