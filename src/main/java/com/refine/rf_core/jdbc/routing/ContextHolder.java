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
package com.refine.rf_core.jdbc.routing;

import com.refine.common.MODE;

/***
* @author mjnet
*
* ThreadLocal을 이용하여 dbMode를 holder에 담아두어 사용하는 모든곳에서 참조하게 한다.
**/
public class ContextHolder {

    private static final ThreadLocal<MODE> contextHolder = new ThreadLocal<MODE>();

    public static void setDbMode(MODE dbMode){
        contextHolder.set(dbMode);
    }

    public static MODE getDbMode(){
        return contextHolder.get();
    }

    public static void clearDbMode(){
        contextHolder.remove();
    }
}