/***
    Copyright (C) 2018 - Refine Inc. <mjnet@refinedev.io>
***
    Licensed under the Apache License, Version 2.0 (the "License");
*    you may not use this file except in compliance with the License.
*    You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*    Unless required by applicable law or agreed to in writing, software
*    distributed under the License is distributed on an "AS IS" BASIS,
*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*    See the License for the specific language governing permissions and
*    limitations under the License.
*/
package com.refine.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public enum MODE {
    REAL("REAL"),
    TEST("TEST"),
    MONGO("MONGO");

    private String mode;
    private static String hostName;
    private static String hostAddr;

    private MODE(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return this.mode;
    }

    /***
     * @return the hostName
     */
    public static String getHostName() {
        return hostName;
    }

    /***
     * @param hostName the hostName to set
     */
    public static void setHostName(String _hostName) {
        hostName = _hostName;
    }

    /***
     * @return the hostAddr
     */
    public static String getHostAddr() {
        return hostAddr;
    }

    /***
     * @param hostAddr the hostAddr to set
     */
    public static void setHostAddr(String _hostAddr) {
        hostAddr = _hostAddr;
    }

    static {
        try {
            System.getProperties().forEach( (k,v) -> System.out.println(k + "=" + v) );
            InetAddress ip = InetAddress.getLocalHost();
            hostName = ip.getHostName();
            hostAddr = ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }  
    }
}