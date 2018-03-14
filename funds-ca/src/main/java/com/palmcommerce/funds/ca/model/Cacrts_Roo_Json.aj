// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.ca.model;

import com.palmcommerce.funds.ca.model.Cacrts;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Cacrts_Roo_Json {
    
    public String Cacrts.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Cacrts Cacrts.fromJsonToCacrts(String json) {
        return new JSONDeserializer<Cacrts>().use(null, Cacrts.class).deserialize(json);
    }
    
    public static String Cacrts.toJsonArray(Collection<Cacrts> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Cacrts> Cacrts.fromJsonArrayToCacrtses(String json) {
        return new JSONDeserializer<List<Cacrts>>().use(null, ArrayList.class).use("values", Cacrts.class).deserialize(json);
    }
    
}
