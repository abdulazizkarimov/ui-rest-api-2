package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Test;
import java.util.List;

public class MapperUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<Test> mapToTestList(String s) {
        List<Test> testList = null;
        try {
            testList = mapper.readValue(s, new TypeReference<List<Test>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return testList;
    }
}