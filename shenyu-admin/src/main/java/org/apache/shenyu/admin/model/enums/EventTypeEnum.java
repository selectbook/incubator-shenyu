/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.admin.model.enums;

import org.apache.shenyu.common.enums.DataEventTypeEnum;

/**
 * EventTypeEnum.
 */
public enum EventTypeEnum {
    
    // ============== created ===================
    /**
     * created event.
     */
    CREATE(DataEventTypeEnum.CREATE, Color.CREATE_COLOR),
    
    /**
     * register event.
     */
    REGISTER("REGISTER", DataEventTypeEnum.CREATE, "#1f640a"),
    
    /**
     * plugin created event.
     */
    PLUGIN_CREATE("CREATE:PLUGIN", DataEventTypeEnum.CREATE, Color.CREATE_COLOR),
    
    /**
     * plugin handle created event.
     */
    PLUGIN_HANDLE_CREATE("CREATE:PLUGIN-HANDLE", DataEventTypeEnum.CREATE, Color.CREATE_COLOR),
    
    /**
     * selector created event.
     */
    SELECTOR_CREATE("CREATE:SELECTOR", DataEventTypeEnum.CREATE, Color.CREATE_COLOR),
    
    // ============== delete ===================
    /**
     * deleted event.
     */
    DELETE(DataEventTypeEnum.DELETE, Color.DELETE_COLOR),
    
    /**
     * clean event.
     */
    CLEAN(DataEventTypeEnum.DELETE, "#e42c64"),
    
    /**
     * plugin deleted event.
     */
    PLUGIN_DELETE("DELETE:PLUGIN", DataEventTypeEnum.DELETE, Color.DELETE_COLOR),
    
    
    /**
     * plugin handle deleted event.
     */
    PLUGIN_HANDLE_DELETE("DELETE:PLUGIN-HANDLE", DataEventTypeEnum.DELETE, Color.DELETE_COLOR),
    
    /**
     * selector deleted event.
     */
    SELECTOR_DELETE("DELETE:SELECTOR", DataEventTypeEnum.DELETE, Color.DELETE_COLOR),
    
    // ============== update ===================
    
    /**
     * update event.
     */
    UPDATE(DataEventTypeEnum.UPDATE, Color.UPDATE_COLOR),
    
    /**
     * plugin update.
     */
    PLUGIN_UPDATE("UPDATE:PLUGIN", DataEventTypeEnum.UPDATE, Color.UPDATE_COLOR),
    
    /**
     * plugin handle update.
     */
    PLUGIN_HANDLE_UPDATE("UPDATE:PLUGIN-HANDLE", DataEventTypeEnum.UPDATE, Color.UPDATE_COLOR),
    
    /**
     * selector update.
     */
    SELECTOR_UPDATE("UPDATE:PLUGIN-HANDLE", DataEventTypeEnum.UPDATE, Color.UPDATE_COLOR);
    
    /**
     * type name.
     */
    private final String typeName;
    
    /**
     * type.
     */
    private final DataEventTypeEnum type;
    
    /**
     * color.
     */
    private final String color;
    
    EventTypeEnum(final DataEventTypeEnum type, final String color) {
        this(type.toString(), type, color);
    }
    
    EventTypeEnum(final String typeName, final DataEventTypeEnum type, final String color) {
        this.typeName = typeName;
        this.type = type;
        this.color = color;
    }
    
    /**
     * get typeName.
     *
     * @return type
     */
    public String getTypeName() {
        return typeName;
    }
    
    /**
     * get type.
     *
     * @return DataEventTypeEnum
     */
    public DataEventTypeEnum getType() {
        return type;
    }
    
    
    /**
     * get color.
     *
     * @return color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * default color.
     */
    private static class Color {
        /**
         * default create event color.
         */
        public static final String CREATE_COLOR = "green";
        
        /**
         * default delete event color.
         */
        public static final String DELETE_COLOR = "red";
        
        /**
         * default update event color.
         */
        public static final String UPDATE_COLOR = "yellow";
    }
    
}
