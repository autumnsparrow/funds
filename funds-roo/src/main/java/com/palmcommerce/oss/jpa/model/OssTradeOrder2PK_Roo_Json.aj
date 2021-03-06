// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeOrder2PK;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect OssTradeOrder2PK_Roo_Json {
    
    public String OssTradeOrder2PK.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static OssTradeOrder2PK OssTradeOrder2PK.fromJsonToOssTradeOrder2PK(String json) {
        return new JSONDeserializer<OssTradeOrder2PK>().use(null, OssTradeOrder2PK.class).deserialize(json);
    }
    
    public static String OssTradeOrder2PK.toJsonArray(Collection<OssTradeOrder2PK> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<OssTradeOrder2PK> OssTradeOrder2PK.fromJsonArrayToOssTradeOrder2PKs(String json) {
        return new JSONDeserializer<List<OssTradeOrder2PK>>().use(null, ArrayList.class).use("values", OssTradeOrder2PK.class).deserialize(json);
    }
    
}
