// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.SelectPartnerPopedomPK;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect SelectPartnerPopedomPK_Roo_Json {
    
    public String SelectPartnerPopedomPK.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static SelectPartnerPopedomPK SelectPartnerPopedomPK.fromJsonToSelectPartnerPopedomPK(String json) {
        return new JSONDeserializer<SelectPartnerPopedomPK>().use(null, SelectPartnerPopedomPK.class).deserialize(json);
    }
    
    public static String SelectPartnerPopedomPK.toJsonArray(Collection<SelectPartnerPopedomPK> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<SelectPartnerPopedomPK> SelectPartnerPopedomPK.fromJsonArrayToSelectPartnerPopedomPKs(String json) {
        return new JSONDeserializer<List<SelectPartnerPopedomPK>>().use(null, ArrayList.class).use("values", SelectPartnerPopedomPK.class).deserialize(json);
    }
    
}
