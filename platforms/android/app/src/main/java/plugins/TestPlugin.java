package plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by liuxiaobing
 * Date on 2018/9/4
 * Copyright 2013 - 2018 QianTuo Inc. All Rights Reserved
 * Desc:
 */

public class TestPlugin extends CordovaPlugin {

    private CallbackContext callbackContext;

    public boolean execute(String action, JSONArray array, CallbackContext callbackContext) throws JSONException{

        this.callbackContext = callbackContext;
        System.out.println("20--------------action:"+action+" : "+array.toString());
        return true;

    }
}
