package com.chatAssistant.utils;
import java.util.List;

public class CalTokensUtils {

    public static int calTokens(String input){
        GPT2Tokenizer tokenizer = GPT2Tokenizer.fromPretrained("./");
        System.out.println("input: " + input);
        List<Integer> result = tokenizer.encode(input);
        return result.size();
    }

}
