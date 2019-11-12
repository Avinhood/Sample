package com.cts.avin.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Util {

    private Util() {
    }
    /*
     * Will create a JsonObject to return it.
     *
     * @return JsonObject.
     * */
    public static JsonObject getListBody() {
        final JsonObject jsonBody = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        try {
            jsonBody.add("Include", jsonArray);
        } catch (Exception e) {

        }
        return jsonBody;
    }
}
