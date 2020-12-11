package com.vyy.scooby;

import java.util.HashMap;

/*

TODO:
Implement cache system to prevent making
DB requests on each event call

 */

public class Cache {
    public static HashMap<Long, String> prefixs = new HashMap<>();
    public static HashMap<Long, Boolean> streamToggle = new HashMap<>();
}
