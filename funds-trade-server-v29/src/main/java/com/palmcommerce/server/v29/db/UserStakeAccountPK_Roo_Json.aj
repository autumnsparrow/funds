// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserStakeAccountPK;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect UserStakeAccountPK_Roo_Json {
    
    public String UserStakeAccountPK.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static UserStakeAccountPK UserStakeAccountPK.fromJsonToUserStakeAccountPK(String json) {
        return new JSONDeserializer<UserStakeAccountPK>().use(null, UserStakeAccountPK.class).deserialize(json);
    }
    
    public static String UserStakeAccountPK.toJsonArray(Collection<UserStakeAccountPK> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<UserStakeAccountPK> UserStakeAccountPK.fromJsonArrayToUserStakeAccountPKs(String json) {
        return new JSONDeserializer<List<UserStakeAccountPK>>().use(null, ArrayList.class).use("values", UserStakeAccountPK.class).deserialize(json);
    }
    
}
