package edu.skku.map.personal_project;

import java.util.HashMap;
import java.util.Map;

public class FabePost {
    public String id;
    public String psword;
    public String realname;
    public String birth;
    public String email;

    public FabePost() {}

    public FabePost(String id, String psword, String realname, String birth, String email)
    {
        this.id = id;
        this.psword = psword;
        this.realname = realname;
        this.birth = birth;
        this.email = email;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("psword", psword);
        result.put("realname", realname);
        result.put("birth", birth);
        result.put("email", email);
        return result;
    }
}
